package com.microblog.chat.handle;

import com.microblog.chat.utils.NettyClientConnectUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class TimeServerHandler extends ChannelHandlerAdapter {

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();

        log.info("TimeServerHandler  exceptionCaught:" + cause.getMessage());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);


        log.info("连接成功！");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        log.info("channelRegistered...");
        NettyClientConnectUtil.addConnectCounter();
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
        log.info("close...");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        NettyClientConnectUtil.decConnectCounter();
        log.info("handlerRemoved...");
    }

    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        //  super.connect(ctx, remoteAddress, localAddress, promise);
        NettyClientConnectUtil.addConnectCounter();

        log.info("connect...");
        log.info("remoteAddress = " + remoteAddress.toString());
        log.info("localAddress = " + localAddress.toString());
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        // super.disconnect(ctx, promise);
        NettyClientConnectUtil.decConnectCounter();
        log.info("disconnect...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super.channelRead(ctx, sms);

        log.info("TimeServerHandler  channelRead");
       /* ByteBuf  buf = (ByteBuf) sms;
        log.info("readableBytes = " + buf.readableBytes());
        byte[] rec = new byte[buf.readableBytes()];

        buf.readBytes(rec);
        String  recStr =  new String(rec,"UTF8");*/
        log.info("接受的数据为：" + msg);
        log.info("counter = " + counter.incrementAndGet());

        String resStr = "我是服务端反馈：" + "123456789";
        ctx.write(Unpooled.copiedBuffer(resStr.getBytes()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //123456super.channelReadComplete(ctx);
        log.info("TimeServerHandler  channelReadComplete");
        ctx.flush();
    }
}
