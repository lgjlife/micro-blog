package com.microblog.msg.rocketmq;

import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.HessianSerializeUtil;
import lombok.Data;
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

    private AbstractSerialize serialize = new HessianSerializeUtil();

    private RocketmqConsumerProperties rocketmqConsumerProperties;

    private DefaultMQPushConsumer  consumer ;
    private String group = "default_group";


    public RocketmqConsumer(RocketmqConsumerProperties rocketmqConsumerProperties){

        this.rocketmqConsumerProperties = rocketmqConsumerProperties;
        create();

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
    public   void create(){

        consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(rocketmqConsumerProperties.getAddress());

    }



    /**
     *功能描述 
     * @author lgj
     * @Description  注册 ConsumerHandle ，在ConsumerHandle 中的handle 进行数据处理
     * @date 4/3/19
     * @param: 
     * @return: 
     *
    */
    public void registerConsumerHandle(ConsumerHandle consumerHandle)  {

        try{
            consumer.subscribe(consumerHandle.getTopic(),consumerHandle.getSubExpression());
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    log.info("接受到的消息：{}", msgs);

                    msgs.forEach((value)->{
                       // log.info("接受到的实际数据={}",new String(value.getBody()));
                        serialize.deserialize(value.getBody(),consumerHandle.getClass());
                        consumerHandle.handle(value);

                    });
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            consumer.start();
        }
        catch(Exception ex){
            log.error("registerConsumerHandle error ! topic = {}, message = {}",consumerHandle.getTopic(),ex.getMessage());
        }


    }


}
