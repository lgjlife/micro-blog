package com.microblog.msgservice.configuration;

import com.microblog.msgservice.mail.MailProperties;
import com.microblog.msgservice.mail.MailSendService;
import com.microblog.msgservice.mail.MailSendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@EnableConfigurationProperties(MailProperties.class)
@Configuration
public class MailAutoConfiguration {

    @Autowired
    private  MailProperties mailProperties;


    @Bean
    JavaMailSenderImpl  javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setDefaultEncoding(mailProperties.getEncoding());
        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setUsername(mailProperties.getSourceMail());
        javaMailSender.setPassword(mailProperties.getPassword());
        javaMailSender.setProtocol(mailProperties.getProtocol());
        return javaMailSender;
    }

    @Bean
    public MailSendService mailSendService(){

        MailSendService mailSendService = new MailSendServiceImpl(javaMailSender());
        return mailSendService;
    }
}
