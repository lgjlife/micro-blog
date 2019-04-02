package com.cloud.microblog.user.service.rocketmq;


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
@ConfigurationProperties(prefix = "rocketmq.producer")
public class RocketProperties {

    private String address = "localhost:9876";
    private String name = "lgj";
    private String password = "lgj";




}
