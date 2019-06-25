package com.microblog.user.service.rocketmq;


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
@EnableConfigurationProperties(RocketProperties.class)
public class RocketmqAutoConfigration {

    @Autowired
    RocketProperties rocketProperties;

    @Bean
    public RocketmqProducer rocketmqProducer(){
        RocketmqProducer producer = new RocketmqProducer(rocketProperties,"USER_PHONE_VERIFICATIONCODE_GROUP");
        return  producer;

    }


    @PostConstruct
    public void   init(){
        log.debug("rocketProperties = " + rocketProperties.toString());
    }
}
