package com.microblog.msgservice.rocketmq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;

/**
 *功能描述 
 * @author lgj
 * @Description  rabbitmq 消费者
 *      RocketmqConsumerProperties properties = new RocketmqConsumerProperties();
 *      RocketmqConsumer consumer = new RocketmqConsumer(properties);
 *      ConsumerHandle  consumerHandle = new PhoneVerifyCodeConsumerHandle();
 *      consumer.registerConsumerHandle(consumerHandle);
 *
 * @date 4/3/19
*/
@Data
@Slf4j
public class RocketmqConsumer {

    private RocketmqConsumerProperties properties;
    private MessageListener messageListener;

    private DefaultMQPushConsumer  consumer ;
    private String group = "msg_group";


    public RocketmqConsumer(RocketmqConsumerProperties properties, MessageListener messageListener) {
        this.messageListener = messageListener;
        this.properties = properties;
    }

    /**
     *功能描述 
     * @author lgj
     * @Description  创建连接
     * @date 4/3/19
     * @param: 
     * @return: 
     *
    */
    public   void start(){

        checkProperties();

        consumer = new DefaultMQPushConsumer(properties.getConsumerGroup());
        consumer.setNamesrvAddr(properties.getAddress());

        try{
            consumer.subscribe(properties.getTopic(),getTags());
            consumer.setMessageListener(messageListener);
            consumer.start();
            log.info("Connect to rocketmq[{}]  success",properties.getAddress());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            log.error("Connect to rocketmq[{}] connect fail",properties.getAddress());
        }
    }

    private String getTags(){


        String[] tags = properties.getTags();
        if (tags.length == 1){
            return tags[0];

        }
        else {

            String tag = tags[1];

            for(int i = 1; i< tags.length; i++){

                tag += "||"+tags[i];
            }

            return tag;
        }
    }

    private void checkProperties(){

        if ((properties.getConsumerGroup() == null)
                ||(properties.getTopic() == null)
                ||(properties.getAddress() == null)
                ||(properties.getTags() == null)){

            throw new IllegalArgumentException("RocketMQ Init Argument can not be null!");
        }
    }
}
