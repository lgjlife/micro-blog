package com.microblog.msg.service.impl;

import com.microblog.common.dto.MailDto;
import com.microblog.msg.message.producer.MqProducer;
import com.microblog.msg.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    MqProducer mqProducer;


    @Override
    public void sendEmail() {

        for(int i = 0; i< 2; i++){


            MailDto mailDto = new MailDto();
            mailDto.setId(100);
            mailDto.setTitle("注册验证码");
            mailDto.setTo("xxxx@163.common");
            mailDto.setTimeStamp(System.currentTimeMillis());
            mailDto.setEffectiveTimeMs(1000*60);
            try{
                mqProducer.asyncSend("microblog-email-topic","",mailDto);
            }
            catch(Exception ex){
                log.error(ex.getMessage());
            }
        }


    }
}
