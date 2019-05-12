package com.microblog.msg.configuration;


import com.microblog.msg.message.consumer.MqConsumer;
import com.microblog.msg.message.handler.EmailMessageHandler;
import com.microblog.msg.message.handler.SMSMessageHandler;
import com.microblog.msg.message.producer.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *功能描述 
 * @author lgj
 * @Description  配置类
 * @date 5/12/19
*/
@Slf4j
@Configuration
public class MsgConfiguration {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     *功能描述 
     * @author lgj
     * @Description   处理mq短信消费者
     * @date 5/12/19
     * @param: 
     * @return: 
     *
    */
    @Bean(name = "SMSMqConsumer")
    public MqConsumer SMSMqConsumer(){
        MqConsumer consumer = new MqConsumer("microblog-sms-group","localhost:9876");
        try{
            consumer.createPushConsumer();
            consumer.pushDataHandler("microblog-sms-topic","*",smsMessageHandler());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }


        return consumer;
    }

    /**
     *功能描述 
     * @author lgj
     * @Description 处理mq邮件消费者  
     * @date 5/12/19
     * @param: 
     * @return: 
     *
    */
    @Bean(name = "EmailMqConsumer")
    public MqConsumer EmailMqConsumer(){
        MqConsumer consumer = new MqConsumer("microblog-email-group","localhost:9876");
        try{
            consumer.createPushConsumer();
            consumer.pushDataHandler("microblog-email-topic","*",emailMessageHandler());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }


        return consumer;
    }


    /**
     *功能描述 
     * @author lgj
     * @Description  生产者，本应用不需要，用于测试 
     * @date 5/12/19
     * @param: 
     * @return: 
     *
    */
    @Bean
    public MqProducer mqProducer(){
        MqProducer producer = new MqProducer("microblog-msg-group","localhost:9876");

        try{

            producer.createProducer();

            return producer;

        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return  null;

    }
    /**
     *功能描述 
     * @author lgj
     * @Description 短信处理类
     * @date 5/12/19
     * @param: 
     * @return: 
     *
    */
    @Bean
    public SMSMessageHandler smsMessageHandler(){
        SMSMessageHandler smsMessageHandler = new SMSMessageHandler(redisTemplate);
        return smsMessageHandler;
    }

    /**
     *功能描述
     * @author lgj
     * @Description 邮件发送处理类
     * @date 5/12/19
     * @param:
     * @return:
     *
    */
    @Bean
    public EmailMessageHandler emailMessageHandler(){
        EmailMessageHandler emailMessageHandler = new EmailMessageHandler(redisTemplate);
        return emailMessageHandler;
    }


}
