package com.cloud.microblog.msg.rocketmq;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 *功能描述 
 * @author lgj
 * @Description  rabbit  属性配置
 * @date 4/2/19
*/
@Data
@ConfigurationProperties(prefix = "rocketmq.consumer")
public class RocketmqConsumerProperties {

    private String address;

}
