package com.cloud.microblog.user.service.rabbitmq;


import lombok.Builder;
import lombok.Data;

/**
 *功能描述 
 * @author lgj
 * @Description  Rabbitmq 发送配置
 * @date 4/2/19
*/

@Builder
@Data
public class PublishConfig {

    /* 队列名称*/
    private  String  queueName;
    /*exchange 交换器名称*/
    private  String  exchangeName;

    /*队列和交换器绑定键*/
    private  String  bingKey;
    /*消息路由键*/
    private  String  routingkey;

}
