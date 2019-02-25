package com.cloud.microblog.chat.coder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 功能描述
 *
 * @author lgj
 * @Description cloud  编解码方式工具类
 * @date 1/23/19
 */
public class CoderUtil {

    public static final String lineBasedFramDecoderSeparator = System.getProperty("line.separator");
    public static final String delimiterBasedFramDecoderSeparator = "$_";
    public static final String fixedBasedFramDecoderSeparator = "";
    public static final String currentSeparator = delimiterBasedFramDecoderSeparator;

    /**
     * 功能描述
     *
     * @author lgj
     * @Description 以 /n   /r/n  作为分隔符
     * @date 1/23/19
     * @param:
     * @return:
     */
    public static void LineBasedFramDecoder(SocketChannel socketChannel, int maxLength) {
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(maxLength));
        socketChannel.pipeline().addLast(new StringDecoder());
    }

    /**
     * 功能描述
     *
     * @author lgj
     * @Description 指定字符串作为分隔符
     * @date 1/23/19
     * @param:
     * @return:
     */
    public static void DelimiterBasedFramDecoder(SocketChannel socketChannel, int maxLength) {

        ByteBuf delimiter = Unpooled.copiedBuffer(delimiterBasedFramDecoderSeparator.getBytes());

        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(maxLength, delimiter));
        socketChannel.pipeline().addLast(new StringDecoder());
    }

    /**
     * 功能描述
     *
     * @author lgj
     * @Description 固定长度字符
     * @date 1/23/19
     * @param:
     * @return:
     */
    public static void FixedBasedFramDecoder(SocketChannel socketChannel, int maxLength) {
        socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(maxLength));
        socketChannel.pipeline().addLast(new StringDecoder());
    }


}
