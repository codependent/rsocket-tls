package com.codependent.rsocket

import io.netty.handler.ssl.SslContextBuilder
import io.rsocket.AbstractRSocket
import io.rsocket.Payload
import io.rsocket.core.RSocketServer
import io.rsocket.frame.decoder.PayloadDecoder
import io.rsocket.transport.netty.server.TcpServerTransport
import io.rsocket.util.DefaultPayload
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.netty.tcp.TcpServer
import java.io.File
import java.util.concurrent.CountDownLatch
import kotlin.random.Random
import kotlin.random.nextUInt

class RequestStreamRSocketServer

@ExperimentalUnsignedTypes
fun main() {

    val latch = CountDownLatch(1)

    RSocketServer.create()
        .payloadDecoder(PayloadDecoder.DEFAULT)
        .acceptor { setup, sendingSocket ->
            Mono.just(
                object : AbstractRSocket() {
                    override fun requestStream(payload: Payload): Flux<Payload> {
                        val randomNumberGenerator = Random(1234)
                        val numbers = payload.dataUtf8.toInt()
                        println("Generating $numbers random numbers")
                        return IntRange(1, numbers)
                            .map { DefaultPayload.create(randomNumberGenerator.nextUInt().toString().toByteArray()) }
                            .toList().toFlux()
                    }
                })
        }.bind(
            TcpServerTransport.create(TcpServer.create().port(7878)
                .secure {
                    it.sslContext(
                        SslContextBuilder.forServer(
                            File(RequestStreamRSocketServer::class.java.getResource("certificate.pem").toURI()),
                            File(RequestStreamRSocketServer::class.java.getResource("key.pem").toURI())
                        )
                    )
                })
        ).block()

    latch.await()
}
