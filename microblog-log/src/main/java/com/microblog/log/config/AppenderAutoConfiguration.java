package com.microblog.log.config;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.microblog.log.anno.EnableKafkaLog;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


@Slf4j
//@Configuration
public class AppenderAutoConfiguration {

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void init(){

        Map<String, Object> annos = applicationContext.getBeansWithAnnotation(EnableKafkaLog.class);
        if(annos.size() == 0){
            return;
        }

        System.out.println("AppenderAutoConfiguration");
        KafkaAppender kafkaAppender =  new KafkaAppender();
        kafkaAppender.setName("KafkaAppender");
        LoggerContext context =(LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggers = context.getLoggerList();

        loggers.forEach((v)->{

          //  v.

          //  v.addAppender(kafkaAppender);
        });
        kafkaAppender.start();
    //     System.out.println(loggers);
    }

  //  @Bean
    public KafkaAppender kafkaAppender(){
        return new KafkaAppender();
    }
}
