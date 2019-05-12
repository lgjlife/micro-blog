package com.microblog.msg.configuration;


import com.microblog.msg.message.consumer.MqConsumer;
import com.microblog.msg.message.handler.EmailMessageHandler;
import com.microblog.msg.message.handler.SMSMessageHandler;
import com.microblog.msg.message.producer.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MsgConfiguration {


    @Bean(name = "SMSMqConsumer")
    public MqConsumer SMSMqConsumer(){
        MqConsumer consumer = new MqConsumer("microblog-sms-group","localhost:9876");
        try{
            consumer.createPushConsumer();
            consumer.pushDataHandler("microblog-sms-topic","*",new SMSMessageHandler());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }


        return consumer;
    }

    @Bean(name = "EmailMqConsumer")
    public MqConsumer EmailMqConsumer(){
        MqConsumer consumer = new MqConsumer("microblog-email-group","localhost:9876");
        try{
            consumer.createPushConsumer();
            consumer.pushDataHandler("microblog-email-topic","*",new EmailMessageHandler());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }


        return consumer;
    }


    @Bean
    public MqProducer mqProducer(){
        MqProducer producer = new MqProducer("microblog-sms-group","localhost:9876");

        try{

            producer.createProducer();

            return producer;

        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
        return  null;

    }

}
