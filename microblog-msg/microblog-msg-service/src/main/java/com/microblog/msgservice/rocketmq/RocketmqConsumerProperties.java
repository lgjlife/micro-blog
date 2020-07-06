package com.microblog.msgservice.rocketmq;


import com.microblog.msgentity.MsgTag;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 *功能描述 
 * @author lgj
 * @Description  rabbit  属性配置
 * @date 4/2/19
*/
@Data
@ConfigurationProperties(prefix = "microblog.rocketmq")
public class RocketmqConsumerProperties {

    /*rocketmq broker地址*/
    private String address;

    private String consumerGroup;

    private String topic;

    private String[] tags = new String[]{MsgTag.SMS_TAG,MsgTag.MAIL_TAG};
}
