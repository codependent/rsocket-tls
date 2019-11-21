package com.codependent.rsocket

import io.netty.handler.ssl.SslContextBuilder
import io.rsocket.RSocketFactory
import io.rsocket.frame.decoder.PayloadDecoder
import io.rsocket.transport.netty.client.TcpClientTransport
import io.rsocket.util.DefaultPayload
import reactor.netty.tcp.TcpClient
import java.util.concurrent.CountDownLatch

class RequestStreamRSocketClient

@ExperimentalUnsignedTypes
fun main() {

    val latch = CountDownLatch(1)

    val path = RequestStreamRSocketClient::class.java.getResource("truststore.jks").path
    System.setProperty("javax.net.ssl.trustStore", path)
    System.setProperty("javax.net.ssl.trustStorePassword", "123456")


    val client = RSocketFactory.connect()
        .frameDecoder(PayloadDecoder.DEFAULT)
        .transport(TcpClientTransport.create(TcpClient.create().port(7878).secure {
            it.sslContext(
                SslContextBuilder.forClient()
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
