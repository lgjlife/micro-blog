package com.microblog.comment.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;



@EnableFeignClients(basePackages="com.microblog.comment.service.feign")
@EnableDiscoveryClient
@MapperScan("com.microblog.commment.dao.mapper")
@ComponentScan(value = {"com.microblog.comment"})
@SpringBootApplication
public class MicroblogCommentWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogCommentWebApplication.class, args);
    }

}
