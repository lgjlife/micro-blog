package com.microblog.blog.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients(basePackages="com.microblog.blog.service.feign")
//扫描 mapper 文件
@MapperScan("com.microblog.blog.dao.mapper")
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(value={"com.microblog.blog","com.microblog.common"})
public class MicroblogBlogApplication {

    public static void main(String args[]){
        SpringApplication.run(MicroblogBlogApplication.class,args);
    }
}
