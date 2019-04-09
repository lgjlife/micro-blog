package com.microblog.blog.service.log;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class AppendConfig {

    @Autowired
    KafkaAppender kafkaAppender;

    @PostConstruct
    public void init(){

        LoggerContext context =(LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggers = context.getLoggerList();
        kafkaAppender.start();
        loggers.forEach((v)->{
            v.addAppender(kafkaAppender);
        });
    //     System.out.println(loggers);
    }

   /* @Bean
    public KafkaAppender kafkaAppender(){
        return new KafkaAppender();
    }*/
}
