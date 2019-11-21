# rsocket-tls

1. Start the server main function in `RequestStreamRSocketServer`
2. Start the client main function in `RequestStreamRSocketClient`

Server side error:

```
Generating 10 random numbers
java.lang.IllegalArgumentException: promise already done: DefaultChannelPromise@447237b5(failure: java.lang.UnsupportedOperationException)
	at io.netty.channel.AbstractChannelHandlerContext.isNotValidPromise(AbstractChannelHandlerContext.java:891)
	at io.netty.channel.AbstractChannelHandlerContext.write(AbstractChannelHandlerContext.java:773)
	at io.netty.channel.AbstractChannelHandlerContext.write(AbstractChannelHandlerContext.java:701)
	at io.netty.handler.ssl.SslHandler.finishWrap(SslHandler.java:899)
	at io.netty.handler.ssl.SslHandler.wrap(SslHandler.java:885)
	at io.netty.handler.ssl.SslHandler.wrapAndFlush(SslHandler.java:797)
	at io.netty.handler.ssl.SslHandler.flush(SslHandler.java:778)
	at io.netty.channel.AbstractChannelHandlerContext.invokeFlush0(AbstractChannelHandlerContext.java:749)
	at io.netty.channel.AbstractChannelHandlerContext.invokeFlush(AbstractChannelHandlerContext.java:741)
	at io.netty.channel.AbstractChannelHandlerContext.flush(AbstractChannelHandlerContext.java:727)
	at reactor.netty.channel.MonoSendMany$SendManyInner$AsyncFlush.run(MonoSendMany.java:621)
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163)
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:416)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:515)
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:918)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)
```

Client side error:
```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
java.nio.channels.ClosedChannelException
	at io.rsocket.RSocketRequester.terminate(RSocketRequester.java:476)
	at io.rsocket.RSocketRequester.lambda$new$0(RSocketRequester.java:94)
	at reactor.core.publisher.FluxDoFinally$DoFinallySubscriber.runFinally(FluxDoFinally.java:156)
	at reactor.core.publisher.FluxDoFinally$DoFinallySubscriber.onComplete(FluxDoFinally.java:139)
	at reactor.core.publisher.MonoProcessor$NextInner.onComplete(MonoProcessor.java:518)
	at reactor.core.publisher.MonoProcessor.onNext(MonoProcessor.java:308)
	at reactor.core.publisher.MonoProcessor.onComplete(MonoProcessor.java:265)
	at io.rsocket.internal.BaseDuplexConnection.dispose(BaseDuplexConnection.java:23)
	at io.rsocket.transport.netty.TcpDuplexConnection.lambda$new$0(TcpDuplexConnection.java:61)
	at io.netty.util.concurrent.DefaultPromise.notifyListener0(DefaultPromise.java:500)
	at io.netty.util.concurrent.DefaultPromise.notifyListeners0(DefaultPromise.java:493)
	at io.netty.util.concurrent.DefaultPromise.notifyListenersNow(DefaultPromise.java:472)
	at io.netty.util.concurrent.DefaultPromise.notifyListeners(DefaultPromise.java:413)
	at io.netty.util.concurrent.DefaultPromise.setValue0(DefaultPromise.java:538)
	at io.netty.util.concurrent.DefaultPromise.setSuccess0(DefaultPromise.java:527)
	at io.netty.util.concurrent.DefaultPromise.trySuccess(DefaultPromise.java:98)
	at io.netty.channel.DefaultChannelPromise.trySuccess(DefaultChannelPromise.java:84)
	at io.netty.channel.AbstractChannel$CloseFuture.setClosed(AbstractChannel.java:1156)
	at io.netty.channel.AbstractChannel$AbstractUnsafe.doClose0(AbstractChannel.java:758)
	at io.netty.channel.AbstractChannel$AbstractUnsafe.close(AbstractChannel.java:734)
	at io.netty.channel.AbstractChannel$AbstractUnsafe.close(AbstractChannel.java:605)
	at io.netty.channel.DefaultChannelPipeline$HeadContext.close(DefaultChannelPipeline.java:1363)
	at io.netty.channel.AbstractChannelHandlerContext.invokeClose(AbstractChannelHandlerContext.java:621)
	at io.netty.channel.AbstractChannelHandlerContext.close(AbstractChannelHandlerContext.java:605)
	at io.netty.channel.AbstractChannelHandlerContext.close(AbstractChannelHandlerContext.java:467)
	at io.netty.handler.ssl.SslHandler.exceptionCaught(SslHandler.java:1092)
	at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:297)
	at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:276)
	at io.netty.channel.AbstractChannelHandlerContext.fireExceptionCaught(AbstractChannelHandlerContext.java:268)
	at io.netty.channel.DefaultChannelPipeline$HeadContext.exceptionCaught(DefaultChannelPipeline.java:1388)
	at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:297)
	at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:276)
	at io.netty.channel.DefaultChannelPipeline.fireExceptionCaught(DefaultChannelPipeline.java:918)
	at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.handleReadException(AbstractNioByteChannel.java:125)
	at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:174)
	at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:697)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:632)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:549)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:511)
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:918)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)
```

If you comment out the TLS configuration, the code works perfectly. Just remove this bit from the server...
```
           .secure {
                it.sslContext(
                    SslContextBuilder.forServer(
                        File(RequestStreamRSocketServer::class.java.getResource("certificate.pem").toURI()),
                        File(RequestStreamRSocketServer::class.java.getResource("key.pem").toURI())
                    )
                )
            }
```

... and this from the client:
```
        .secure {
            it.sslContext(
                SslContextBuilder.forClient()
            )
        }
```

Server logs:
```
Generating 10 random numbers
```
Client logs:
```
345130239
2958210271
3979283303
4254072378
4206518657
1432197826
3787126071
2479634382
4147073748
3864383859
```



