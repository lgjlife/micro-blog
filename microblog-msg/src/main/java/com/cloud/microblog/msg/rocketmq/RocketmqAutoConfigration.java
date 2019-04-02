package com.cloud.microblog.msg.rocketmq;


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
@EnableConfigurationProperties(RocketmqConsumerProperties.class)
public class RocketmqAutoConfigration {

    @Autowired
    RocketmqConsumerProperties rocketmqConsumerProperties;

    @Bean
    public RocketmqConsumer rocketmqConsumer(){
        RocketmqConsumer consumer = new RocketmqConsumer(rocketmqConsumerProperties);
        consumer.create();
        consumer.registerConsumerHandle(consumerHandle());
        return  consumer;

    }

    @Bean
    public PhoneVerifyCodeConsumerHandle consumerHandle(){
        return  new PhoneVerifyCodeConsumerHandle();
    };

    @PostConstruct
    public void   init(){
        log.debug("rocketProperties = " + rocketmqConsumerProperties.toString());
    }
}
