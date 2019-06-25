package com.log.config;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.log.anno.EnableKafkaLog;
import com.log.kafka.KafkaConfig;
import com.log.kafka.LogKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
@ConditionalOnBean(KafkaConfig.class)
public class AppenderAutoConfiguration {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private KafkaConfig kafkaConfig;

    @Bean
    public LogKafkaProducer logKafkaProducer(){
        //properties.getKafkaCfg()
        LogKafkaProducer logKafkaProducer =  new LogKafkaProducer(kafkaConfig.getConfig());
        return logKafkaProducer;
    }

    @Bean
    public KafkaAppender kafkaAppender(){

        Map<String, Object> annoClz = context.getBeansWithAnnotation(EnableKafkaLog.class);
        if(annoClz.size() == 0){
            log.warn("Un Enable  the log send to kafka, if you want send ,please use  @EnableKafkaLog ");
            return null;
        }

        KafkaAppender kafkaAppender = new KafkaAppender();
        kafkaAppender.setProducer(logKafkaProducer());

        kafkaAppender.setTopic(kafkaConfig.getTopic());
        kafkaAppender.setApplicationName(kafkaConfig.getAppName());
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

        kafkaAppender.start();
        kafkaOutputStreamAppender.start();
        return kafkaAppender;
    }

}
