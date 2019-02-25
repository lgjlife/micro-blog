package com.cloud.microblog.chat.handle;

import com.cloud.microblog.chat.utils.NettyClientConnectUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;


@Slf4j
public class NettyConnectServerHandler extends ChannelInboundHandlerAdapter {

    @Override
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
}
