package com.microblog.user.service.rabbitmq;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 *功能描述 
 * @author lgj
 * @Description  rabbit  属性配置
 * @date 4/2/19
*/
@Data
@Configuration
@ConfigurationProperties(prefix = "rabbitmq.producer")
public class RabbitmqProperties {

    private String host = "localhost";
    private int port = 5672;
    private String name = "lgj";
    private String password = "lgj";

}
