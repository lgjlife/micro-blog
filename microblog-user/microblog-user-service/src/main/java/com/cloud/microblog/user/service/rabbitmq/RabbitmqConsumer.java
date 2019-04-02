package com.cloud.microblog.user.service.rabbitmq;

import com.cloud.microblog.user.dao.model.User;
import com.rabbitmq.client.*;
import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.HessianSerializeUtil;
import lombok.Data;

import java.io.IOException;

@Data
public class RabbitmqConsumer {

    private static  String EXCHANGE_NAME = "my-exchange";
    private static  String QUEUE_NAME = "my-queue";
    private static  String ROUTING_KEY = "my-routing";
    private static  String host = "localhost";
    private static  int port = 5672;


    private AbstractSerialize serialize = new HessianSerializeUtil();


    private Connection connection;

    public static  RabbitmqConsumer create(){
        RabbitmqConsumer consumer = new RabbitmqConsumer();
        Address[]  addresses = new Address[]{
            new Address(host,port)
        };
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("lgj");
        factory.setPassword("lgj");

        try{
            Connection connection = factory.newConnection(addresses);
            consumer.setConnection(connection);

            return consumer;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return  null;

    }


    public void  recdata() throws Exception{

        final  Channel  channel = connection.createChannel();



        channel.basicQos(64);
        Consumer consumer  = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               // System.out.println("接收到的数据：" + new String(body));

                User obj = serialize.deserialize(body,User.class);

                System.out.println(obj.toString());

                channel.basicAck(envelope.getDeliveryTag(),false);
            }

        };

        channel.basicConsume(QUEUE_NAME,consumer);

    }


}
