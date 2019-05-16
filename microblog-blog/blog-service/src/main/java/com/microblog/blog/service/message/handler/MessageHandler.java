package com.microblog.blog.service.message.handler;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public interface MessageHandler {

    ConsumeConcurrentlyStatus handler(List<MessageExt> msgs);
}
