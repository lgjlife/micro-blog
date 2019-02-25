package com.cloud.microblog.chat.coder;

import com.cloud.microblog.chat.serialize.JdkSerialize;
import com.cloud.microblog.chat.serialize.Serialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdkEncoder extends MessageToByteEncoder<Object> {

    /*@Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          Object o,
                          List<Object> list) throws Exception {

        log.info("JdkEncoder encode....");

        log.info("Object = " + o);
        Serialize serialize = new JdkSerialize();
        byte[] b = serialize.writeObject(o);
        list.add(b);

    }*/

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        log.info("JdkEncoder encode....");

        log.info("Object = " + o);
        Serialize serialize = new JdkSerialize();
        byte[] b = serialize.writeObject(o);
        byteBuf.writeBytes(b);
    }
}
