package com.microblog.blog.service.message.consumer;

import com.microblog.blog.service.message.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;


/**
 *功能描述
 * @author lgj
 * @Description  rocketmq 消费端
 * @date 5/12/19
*/
@Slf4j
public class MqConsumer {

    private DefaultMQPushConsumer pushConsumer;

    private String group;
    private String namesrvAddr;


    public MqConsumer(String group, String namesrvAddr) {
        this.group = group;
        this.namesrvAddr = namesrvAddr;
    }

    /**
     *功能描述
     * @author lgj
     * @Description 创建消费者
     * @date 5/12/19
     * @param:
     * @return:
     *
    */
    public  void createPushConsumer() throws  Exception{

        pushConsumer = new DefaultMQPushConsumer(group);
        pushConsumer.setNamesrvAddr(namesrvAddr);

    }


    public void pushDataHandler(String topic, String subExpression, MessageHandler handler)throws  Exception{

        pushConsumer.subscribe(topic,subExpression);
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                log.info("sms 消费者接收到消息");
                return handler.handler(msgs);
            }
        });
        pushConsumer.start();
    }
}
