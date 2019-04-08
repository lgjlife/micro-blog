package com.microblog.msg.rabbitmq;


public interface   ConsumerHandle<T> {

     String getQueueName();

     void  handle(Object object);

    Class getTClass();
}
