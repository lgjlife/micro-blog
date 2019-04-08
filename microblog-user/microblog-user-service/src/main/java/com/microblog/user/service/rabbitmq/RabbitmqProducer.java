package com.microblog.user.service.rabbitmq;

import com.rabbitmq.client.*;
import com.utils.serialization.AbstractSerialize;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 *功能描述
 * @author lgj
 * @Description  rabbitmq 生产者
 * @date 3/22/19
*/

@Slf4j
@Data
public class RabbitmqProducer {

    private static  String EXCHANGE_NAME = "my-exchange";
    private static  String QUEUE_NAME = "my-queue";


    private RabbitmqProperties rabbitmqProperties;

    private AbstractSerialize  serialize ;

    private Connection connection;

    public RabbitmqProducer(RabbitmqProperties properties,AbstractSerialize serialize){
        this.rabbitmqProperties = properties;
        this.serialize = serialize;
        create();
        
    }


    /**
     *功能描述 
     * @author lgj
     * @Description  创建连接
     * @date 4/2/19
     * @param: 
     * @return: 
     *
    */
    public   void  create(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(rabbitmqProperties.getName());
        factory.setPassword(rabbitmqProperties.getPassword());
        factory.setHost(rabbitmqProperties.getHost());
        factory.setPort(rabbitmqProperties.getPort());

        try{
            this.connection  = factory.newConnection();
        }
        catch(Exception ex){
            log.error("create RabbitMq connection error,message = {}",ex.getMessage());
        }
    }


    /**
     *功能描述 
     * @author lgj
     * @Description  推送消息
     * @date 4/2/19
     * @param: 
     * @return: 
     *
    */
    public void  publish(Object msg,PublishConfig config){
        Channel channel = null;

        try{
            channel = connection.createChannel();
           // AMQP.Exchange.DeclareOk declareOk =  channel.exchangeDeclare(EXCHANGE_NAME,ExchangeType.DIRECT_TYPE,true,false,null);
           // channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            //绑定queue和exchange
            channel.queueBind(config.getQueueName(),
                    config.getExchangeName(),
                    config.getBingKey());
            //根据路由键发布消息
            byte[] sendData = serialize.serialize(msg);
            channel.basicPublish(config.getExchangeName(),config.getRoutingkey(),
                    MessageProperties.PERSISTENT_TEXT_PLAIN,sendData);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            try{
                channel.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

}
