package com.cloud.microblog.chat.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.msgpack.MessagePack;

import java.util.List;

@Slf4j
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List list) throws Exception {
        log.info("MsgpackDecoder decode....");
        final byte[] bytes;
        final int length = byteBuf.readableBytes();
        if (length <= 0) return;
        bytes = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, length);

        log.info("byte len = " + bytes.length);
//      调用MessagePack 的read方法将其反序列化为Object对象
        MessagePack msgPack = new MessagePack();
        list.add(msgPack.read(bytes));
    }
}
