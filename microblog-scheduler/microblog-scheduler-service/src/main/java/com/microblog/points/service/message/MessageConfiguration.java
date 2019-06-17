package com.microblog.points.service.message;


import com.microblog.points.service.message.producer.MqProducer;
import lombok.extern.slf4j.Slf4j;
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
public class MessageConfiguration {


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




}
