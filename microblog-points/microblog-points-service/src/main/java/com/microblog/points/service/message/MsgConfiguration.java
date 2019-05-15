package com.microblog.points.service.message;


import com.microblog.points.service.PointsService;
import com.microblog.points.service.message.consumer.MqConsumer;
import com.microblog.points.service.message.handler.PointsMessageHandler;
import com.microblog.points.service.message.producer.MqProducer;
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
    private PointsService pointsService;

    /**
     *功能描述 
     * @author lgj
     * @Description   处理mq短信消费者
     * @date 5/12/19
     * @param: 
     * @return: 
     *
    */
    @Bean(name = "pointsConsumer")
    public MqConsumer SMSMqConsumer(){
        MqConsumer consumer = new MqConsumer("microblog-points-group","localhost:9876");
        try{
            consumer.createPushConsumer();
            consumer.pushDataHandler("microblog-points-topic","*",pointsMessageHandler());
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
    PointsMessageHandler pointsMessageHandler(){

        PointsMessageHandler handler = new PointsMessageHandler(pointsService);
        return handler;
    }

}
