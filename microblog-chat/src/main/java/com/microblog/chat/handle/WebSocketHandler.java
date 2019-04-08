package com.microblog.chat.handle;

import com.microblog.chat.dto.ChatUserDto;
import com.microblog.chat.service.UserInfoManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;


@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private WebSocketServerHandshaker handshaker;
    private final String wsUri = "/ws";
    //websocket握手升级绑定页面
    String wsFactoryUri = "";

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object o) throws Exception {

        log.info("WebSocketHandler  messageReceived");
        if (o instanceof FullHttpRequest) {
            log.info("传统接入");

            TextWebSocketFrame tws = new TextWebSocketFrame("我来自服务端");
            ctx.channel().writeAndFlush(tws);

        } else if (o instanceof WebSocketFrame) {
            log.info("web socket 接入");
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("握手建立");
        channels.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("握手取消");
        channels.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("WebSocketHandler  exceptionCaught");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("WebSocketHandler  channelReadComplete");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端与服务端连接 + " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端与服务端连接关闭 + " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端发来信息 + " + ctx.channel().remoteAddress());
        //   log.info("sms = " + sms);

        if (msg instanceof FullHttpRequest) {
            log.info("传统接入");
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            log.info("web socket 接入");
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }


    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws UnsupportedEncodingException {
        // 如果HTTP解码失败，返回HHTP异常
        if (req instanceof HttpRequest) {
            HttpMethod method = req.getMethod();
            // 如果是websocket请求就握手升级
            if (wsUri.equalsIgnoreCase(req.getUri())) {
                System.out.println(" req instanceof HttpRequest");
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                        wsFactoryUri, null, false);
                handshaker = wsFactory.newHandshaker(req);
                if (handshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());

                } else {
                    handshaker.handshake(ctx.channel(), req);
                }
            }

        }
    }

    /*
     * 处理Websocket的代码
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        //System.out.println("chat get");
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 文本消息，不支持二进制消息
        if (frame instanceof TextWebSocketFrame) {
            // 返回应答消息
            String requestmsg = ((TextWebSocketFrame) frame).text();

            log.info("websocket消息======" + requestmsg);
            String[] array = requestmsg.split(",");
            // 将通道加入通道管理器
            UserInfoManager.addChannel(ctx.channel(), array[0]);
            ChatUserDto userInfo = UserInfoManager.getUserInfo(ctx.channel());
            if (array.length == 3) {
                // 将信息返回给h5
                String sendid = array[0];
                String friendid = array[1];
                String messageid = array[2];
                log.info("sendid = " + sendid
                        + "  friendid " + friendid
                        + "  messageid " + messageid);
                UserInfoManager.broadcastMess(friendid, messageid, sendid);
            }
            UserInfoManager.showUserInfo();
        }
    }

}
