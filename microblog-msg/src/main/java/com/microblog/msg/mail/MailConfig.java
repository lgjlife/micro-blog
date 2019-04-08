package com.microblog.msg.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    JavaMailSenderImpl  javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setHost("smtp.sina.com");
        javaMailSender.setUsername("lanmeishop1@sina.com");
        javaMailSender.setPassword("lanmei");
        javaMailSender.setProtocol("smtp");
        return javaMailSender;
    }
}
