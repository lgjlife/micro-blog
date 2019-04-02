package com.cloud.microblog.user.service.rocketmq;


import lombok.Builder;
import lombok.Data;

/**
 *功能描述 
 * @author lgj
 * @Description  Rocketmq 发送配置
 * @date 4/2/19
*/

@Builder
@Data
public class RocketmqPublishConfig {


    private  String  topic;

    private  String  tags;



}
