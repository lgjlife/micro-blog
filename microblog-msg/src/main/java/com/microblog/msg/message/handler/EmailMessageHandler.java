package com.microblog.msg.message.handler;

import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.JdkSerializeUtil;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class EmailMessageHandler implements MessageHandler{

    private AbstractSerialize serialize = new JdkSerializeUtil();

    @Override
    public ConsumeConcurrentlyStatus handler(List<MessageExt> msgs) {
        return null;
    }
}
