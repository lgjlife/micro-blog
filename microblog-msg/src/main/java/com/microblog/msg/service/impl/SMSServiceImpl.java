package com.microblog.msg.service.impl;

import com.microblog.common.dto.SMSDto;
import com.microblog.msg.message.producer.MqProducer;
import com.microblog.msg.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SMSServiceImpl implements SMSService {

    @Autowired
    MqProducer mqProducer;

    @Override
    public void sendSms() {

        SMSDto smsDto = new SMSDto();
        smsDto.setPhone("13925716752");
        smsDto.setCode("123456");
        smsDto.setEffectiveTimeMs(1000);
        smsDto.setTimeStamp(System.currentTimeMillis());
        try{
            mqProducer.asyncSend("microblog-sms-topic","",smsDto);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

    }
}
