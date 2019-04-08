package com.microblog.user.service.rabbitmq;

/**
 *功能描述
 * @author lgj
 * @Description  rabbitmq 交换器类型
 * @date 3/22/19
*/
public class ExchangeType {

    public  static  String FANOUT_TYPE = "fanout";
    public  static  String DIRECT_TYPE = "direct";
    public  static  String TOPIC_TYPE = "topic";
    public  static  String HEADER_TYPE = "header";


}
