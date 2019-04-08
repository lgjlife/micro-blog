package com.microblog.chat.coder;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JsonDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List list) throws Exception {


        log.info("JdkDecoder decode....");
        byte[] b = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(b);
        log.info(new String(b));
        Object obj = JSON.parseObject(new String(b));
        list.add(obj);
    }
}
