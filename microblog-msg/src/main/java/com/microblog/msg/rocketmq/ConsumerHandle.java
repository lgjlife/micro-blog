package com.microblog.msg.rocketmq;


public interface   ConsumerHandle<T> {

     String getTopic();
     String getSubExpression();

     void  handle(Object object);

    Class getTClass();
}
