package com.cloud.microblog.chat.service;


import com.cloud.microblog.chat.handle.WebSocketChildChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service

@Slf4j

public class WebSocketServer {

    public void run(int port) throws Exception {

        log.info("service bind port = " + port);
        //bossGroup接受传入的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //一旦bossGroup接受连接并注册到workerGroup，workerGroup则处理连接相关的流量
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //用于设置服务端
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // .option(ChannelOption.SO_BACKLOG,1024)
                    //  .childHandler(new ChildChannelHandler());
                    .childHandler(new WebSocketChildChannelHandler())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //绑定端口，同步等待成功
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            Channel channel = channelFuture.channel();

            log.info("绑定端口，同步等待成功");
            //等待服务端监听端口关闭
            //   channelFuture.channel().close().sync();
            log.info("等待服务端监听端口关闭");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            log.info("shutdownGracefully....");
            //  bossGroup.shutdownGracefully();
            //  workerGroup.shutdownGracefully();
        }
    }
}
