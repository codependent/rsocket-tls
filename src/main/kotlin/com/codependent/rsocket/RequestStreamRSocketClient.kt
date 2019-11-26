package com.codependent.rsocket

import io.netty.handler.ssl.SslContextBuilder
import io.rsocket.RSocketFactory
import io.rsocket.frame.decoder.PayloadDecoder
import io.rsocket.transport.netty.client.TcpClientTransport
import io.rsocket.util.DefaultPayload
import reactor.netty.tcp.TcpClient
import java.io.File
import java.util.concurrent.CountDownLatch

class RequestStreamRSocketClient

@ExperimentalUnsignedTypes
fun main() {

    val latch = CountDownLatch(1)

    val client = RSocketFactory.connect()
        .frameDecoder(PayloadDecoder.DEFAULT)
        .transport(TcpClientTransport.create(TcpClient.create().port(7878).secure {
            it.sslContext(
                SslContextBuilder.forClient().trustManager(File(RequestStreamRSocketClient::class.java.getResource("certificate.pem").path))
            )
        }))
        .start()
        .block()

    client.requestStream(DefaultPayload.create("10"))
        .map { it.dataUtf8 }
        .doOnNext(System.out::println)
        .doOnComplete { latch.countDown() }
        .doOnError { it.printStackTrace() }
        .subscribe()

    latch.await()
}
