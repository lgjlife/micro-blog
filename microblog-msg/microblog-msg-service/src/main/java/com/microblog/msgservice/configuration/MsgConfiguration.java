package com.microblog.msgservice.configuration;


import com.microblog.msgservice.mail.MailSendService;
import com.microblog.msgservice.rocketmq.MsgMessageListener;
import com.microblog.msgservice.rocketmq.RocketmqConsumer;
import com.microblog.msgservice.rocketmq.RocketmqConsumerProperties;
import com.microblog.msgservice.sender.DefaultMailSender;
import com.microblog.msgservice.sender.MailSender;
import com.microblog.msgservice.service.MsgMailHandler;
import com.microblog.msgservice.service.MsgSMSHandler;
import com.microblog.msgservice.service.impl.MsgMailHandlerImpl;
import com.microblog.msgservice.service.impl.MsgSMSHandlerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *功能描述 
 * @author lgj
 * @Description  配置类
 * @date
*/
@Slf4j
@Configuration
@EnableConfigurationProperties(RocketmqConsumerProperties.class)
public class MsgConfiguration {

    @Autowired
    private RocketmqConsumerProperties properties;

    @Autowired
    private MailSendService mailSendService;

    @Bean
    public RocketmqConsumer rocketmqConsumer(){
        RocketmqConsumer consumer = new RocketmqConsumer(properties,msgMessageListener());
        consumer.start();
        return  consumer;
    }

    @Bean
    public MsgMessageListener msgMessageListener(){
        MsgMessageListener listener = new MsgMessageListener();
        listener.setMsgMailHandler(mailService());
        listener.setMsgSmsHandler(smsService());
        return listener;
    }

    @Bean
    public MailSender mailSender(){

        MailSender mailSender = new DefaultMailSender(mailSendService);
        return mailSender;
    }

    @Bean
    public MsgMailHandler mailService(){
        MsgMailHandler msgMailHandler =  new MsgMailHandlerImpl(mailSender());
        return msgMailHandler;
    }

    @Bean
    public MsgSMSHandler smsService(){
        MsgSMSHandler msgSmsHandler =  new MsgSMSHandlerImpl();
        return msgSmsHandler;
    }


}
