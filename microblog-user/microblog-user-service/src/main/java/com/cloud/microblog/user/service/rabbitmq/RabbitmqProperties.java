package com.cloud.microblog.user.service.rabbitmq;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 *功能描述 
 * @author lgj
 * @Description  rabbit  属性配置
 * @date 4/2/19
*/
@Data
@ConfigurationProperties(prefix = "rabbitmq.producer")
public class RabbitmqProperties {

    private String host = "localhost";
    private int port = 5672;
    private String name = "lgj";
    private String password = "lgj";

}
