package com.cloud.microblog.msg.rabbitmq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


/**
 *功能描述 
 * @author lgj
 * @Description  rabbit  模块 bean 配置
 * @date 4/2/19
*/
@Slf4j
@Configuration
@EnableConfigurationProperties(RabbitmqConsumerProperties.class)
public class RabbitmqAutoConfigration {

    @Autowired
    RabbitmqConsumerProperties rabbitmqProperties;

    @Bean
    public RabbitmqConsumer producer(){
        RabbitmqConsumer consumer = new RabbitmqConsumer(rabbitmqProperties);
        consumer.registerConsumerHandle(mailConsumerHandle());
        return  consumer;

    }

    @Bean
    public  MailConsumerHandle mailConsumerHandle(){
        return  new MailConsumerHandle();
    };

    @PostConstruct
    public void   init(){
        log.debug("rabbitmqProperties = " + rabbitmqProperties.toString());
    }
}
