package com.microblog.msg.controller;


import com.microblog.msg.service.EmailService;
import com.microblog.msg.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
