package com.microblog.chat.coder;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JsonEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        log.info("JdkEncoder encode....");
      /*  log.info("Object = " + o);


        TestUser user = new TestUser("aaa",1,1);
        String str1 = JSON.toJSONString(user);
        log.info("json obj = " + str1);*/


        String str = JSON.toJSONString(o);
        //log.info("json obj = " + str);
        byteBuf.writeBytes(str.getBytes());
    }
}
