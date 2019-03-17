package com.cloud.microblog.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@MapperScan("com.cloud.microblog.user.dao.mapper")
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud.microblog.blog.*","com.cloud.microblog.common"})
public class BlogApplication {

    public static void main(String args[]){
        SpringApplication.run(BlogApplication.class,args);

    }
}
