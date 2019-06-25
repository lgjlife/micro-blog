package com.microblog.msg.controller;


import com.microblog.msg.service.EmailService;
import com.microblog.msg.service.SMSService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/message",description = "消息 controller")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    SMSService smsService;

    @Autowired
    HttpServletRequest request;



    @Autowired
    EmailService emailService;



    @RequestMapping("/sms")
    public void sendSms(){
        smsService.sendSms();
    }

    @RequestMapping("/email")
    public void sendEmail(){
        emailService.sendEmail();
    }



}
