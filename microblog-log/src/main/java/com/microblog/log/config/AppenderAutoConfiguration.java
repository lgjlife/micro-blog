package com.microblog.log.config;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.microblog.log.anno.EnableKafkaLog;
import com.microblog.log.kafka.LogKafkaProducer;
import com.microblog.log.properties.LogAppenderProperties;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


/**
 *功能描述
 * @author lgj
 * @Description
 * @date 4/10/19
*/
@Slf4j
@Configuration
@EnableConfigurationProperties(LogAppenderProperties.class)
public class AppenderAutoConfiguration {

    @Autowired
    ApplicationContext context;

    @Autowired
    LogAppenderProperties properties;

    @PostConstruct
    public void init(){
       Map<String, Object> annoClz = context.getBeansWithAnnotation(EnableKafkaLog.class);
        if(annoClz.size() == 0){
            log.warn("Un Enable  the log send to kafka, if you want send ,please use  @EnableKafkaLog ");
            return;
        }
        System.out.println("properties = " + properties);

        Map<String ,Object> kafkacfg = LogKafkaProducer.defaultConfig();

        KafkaAppender kafkaAppender =  new KafkaAppender();
        kafkaAppender.setProducerCfg(properties.getKafkaCfg());
        kafkaAppender.setTopic(properties.getTopic());
        kafkaAppender.setName("KafkaAppender");

        LoggerContext context =(LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggers = context.getLoggerList();

        KafkaOutputStreamAppender kafkaOutputStreamAppender  = new KafkaOutputStreamAppender();
        kafkaOutputStreamAppender.setName("kafkaOutputStreamAppender");
        kafkaOutputStreamAppender.setContext(context);
        kafkaOutputStreamAppender.setEncoder(new LogstashEncoder());


        //使用Java方式拦截Logger多次调用的问题，只给ROOT logger添加Appender
        loggers.stream().filter((v)->{
            return v.getName().equals("ROOT");
        }).forEach((v)->{
            v.addAppender(kafkaAppender);
            v.addAppender(kafkaOutputStreamAppender);
        });

        /*loggers.forEach((v)->{
            if(v.getName().equals("ROOT")){
                System.out.println("ROOT = " + v.getName());
               v.addAppender(kafkaAppender);

                v.addAppender(kafkaOutputStreamAppender);
            }
        });*/
        kafkaAppender.start();
        kafkaOutputStreamAppender.start();

    }
}
