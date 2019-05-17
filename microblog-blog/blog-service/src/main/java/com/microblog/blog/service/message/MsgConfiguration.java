package com.microblog.blog.service.message;


import com.microblog.blog.service.handler.BlogLikeHandler;
import com.microblog.blog.service.message.consumer.MqConsumer;
import com.microblog.blog.service.message.handler.BlogLikeImportMessageHandler;
import com.microblog.blog.service.message.producer.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *功能描述 
 * @author lgj
 * @Description  配置类
 * @date 5/12/19
*/
@Slf4j
@Configuration
public class MsgConfiguration {

    @Autowired
    private BlogLikeHandler blogLikeHandler;

    /**
     *功能描述 
     * @author lgj
     * @Description   处理mq短信消费者
     * @date 5/12/19
     * @param: 
     * @return: 
     *
    */
    @Bean(name = "LikeImportConsumer")
    public MqConsumer LikeImportMqConsumer(){
        MqConsumer consumer = new MqConsumer("microblog-blog-group","localhost:9876");
        try{
            consumer.createPushConsumer();
            consumer.pushDataHandler("microblog-blog-topic","like.import.enable",blogLikeImportMessageHandler());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return consumer;
    }

    /**
     *功能描述 
     * @author lgj
     * @Description  生产者，本应用不需要，用于测试 
     * @date 5/12/19
     * @param: 
     * @return: 
     *
    */
    @Bean
    public MqProducer mqProducer(){
        MqProducer producer = new MqProducer("microblog-points-group","localhost:9876");
        try{
            producer.createProducer();
            return producer;
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return  null;
    }


    @Bean
    BlogLikeImportMessageHandler blogLikeImportMessageHandler(){

        BlogLikeImportMessageHandler handler = new BlogLikeImportMessageHandler(blogLikeHandler);
        return handler;
    }


}
