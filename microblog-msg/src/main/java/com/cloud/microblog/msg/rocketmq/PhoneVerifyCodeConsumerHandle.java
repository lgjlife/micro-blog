package com.cloud.microblog.msg.rocketmq;


import com.cloud.microblog.common.dto.PhoneVerifCodeDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneVerifyCodeConsumerHandle implements ConsumerHandle {

     private  String topic = "PHONE_VERIFY_CODE_TOPIC";
     private  String subExpression = "*";

     @Override
     public String getTopic() {
          return topic;
     }

     @Override
     public String getSubExpression() {
          return subExpression;
     }



     @Override
     public void  handle(Object obj){
          PhoneVerifCodeDto dto = (PhoneVerifCodeDto)obj;

          log.debug("PhoneVerifCodeDto = {}",dto);
     }

     public Class getTClass()
     {
         return  PhoneVerifCodeDto.class;
     }

}
