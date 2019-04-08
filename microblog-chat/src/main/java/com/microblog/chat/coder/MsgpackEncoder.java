package com.microblog.chat.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.msgpack.MessagePack;

@Slf4j
public class MsgpackEncoder extends MessageToByteEncoder<Object> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        log.info("MsgpackEncoder encode....");
        log.info("Object = " + o);

        MessagePack msgPack = new MessagePack();
//      编码，然后转为ButyBuf传递
        byte[] bytes = msgPack.write(o);
        byteBuf.writeBytes(bytes);
    }
}
