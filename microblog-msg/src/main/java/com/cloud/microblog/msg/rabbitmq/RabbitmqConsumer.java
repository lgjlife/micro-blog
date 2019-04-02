package com.cloud.microblog.msg.rabbitmq;

import com.rabbitmq.client.*;
import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.HessianSerializeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

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
public class RabbitmqConsumer {

    private AbstractSerialize serialize = new HessianSerializeUtil();

    private RabbitmqConsumerProperties rabbitmqProperties;

    private Connection connection;

    public RabbitmqConsumer(RabbitmqConsumerProperties rabbitmqProperties){

        this.rabbitmqProperties = rabbitmqProperties;
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

        Address[]  addresses = new Address[]{
            new Address(rabbitmqProperties.getHost(),rabbitmqProperties.getPort())
        };
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(rabbitmqProperties.getName());
        factory.setPassword(rabbitmqProperties.getPassword());

        try{
            this.connection = factory.newConnection(addresses);
        }
        catch(Exception ex){
            log.error("create rabbitmq consumer error! message = {}",ex.getMessage());
        }

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
    public void registerConsumerHandle(ConsumerHandle  consumerHandle)  {

        try{
            final Channel channel = connection.createChannel();

            channel.basicQos(64);

            Consumer consumer  = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // System.out.println("接收到的数据：" + new String(body));

                    Object obj = serialize.deserialize(body,consumerHandle.getTClass());

                    // System.out.println(obj.toString());
                    consumerHandle.handle(obj);

                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            };
            channel.basicConsume(consumerHandle.getQueueName(),consumer);
        }
        catch(Exception ex){
            log.error("registerConsumerHandle error ! message = {}",ex.getMessage());
        }

    }


}
