package com.microblog.msg.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@EnableConfigurationProperties(MailProperties.class)
@Configuration
public class MailConfig {

    @Autowired
    private  MailProperties mailProperties;


    @Bean
    JavaMailSenderImpl  javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setDefaultEncoding(mailProperties.getEncoding());
        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setPassword(mailProperties.getPassword());
        javaMailSender.setProtocol(mailProperties.getProtocol());
        return javaMailSender;
    }
}
