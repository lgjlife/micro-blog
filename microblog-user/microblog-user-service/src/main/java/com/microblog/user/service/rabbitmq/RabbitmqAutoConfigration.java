package com.microblog.user.service.rabbitmq;


import com.utils.serialization.ProtostuffSerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


/**
 *功能描述 
 * @author lgj
 * @Description  rabbit  模块 bean 配置
 * @date 4/2/19
*/
@Slf4j
//@Configuration
@EnableConfigurationProperties(RabbitmqProperties.class)
public class RabbitmqAutoConfigration {

    @Autowired
    RabbitmqProperties rabbitmqProperties;

    @Bean
    public RabbitmqProducer rabbitmqProducer(){
        RabbitmqProducer producer = new RabbitmqProducer(rabbitmqProperties,
                 new ProtostuffSerializeUtil());

        return  producer;

    }


    @PostConstruct
    public void   init(){
        log.debug("rocketProperties = " + rabbitmqProperties.toString());
    }
}
