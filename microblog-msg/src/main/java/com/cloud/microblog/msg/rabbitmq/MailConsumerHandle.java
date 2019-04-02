package com.cloud.microblog.msg.rabbitmq;


import com.cloud.microblog.common.dto.MailDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class  MailConsumerHandle  implements  ConsumerHandle {

     private  String queueName = "USER_MAIL_QUEUE_NAME";

     @Override
     public  String getQueueName(){

          return  queueName;
     }

     @Override
     public void  handle(Object obj){
          MailDto mailDto = (MailDto)obj;

          log.debug("mailDto = {}",mailDto);
     }

     public Class getTClass()
     {
         return  MailDto.class;
     }

}
