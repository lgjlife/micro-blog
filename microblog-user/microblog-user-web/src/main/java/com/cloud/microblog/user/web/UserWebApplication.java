package com.cloud.microblog.user.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@EnableScheduling


@EnableFeignClients
@MapperScan("com.cloud.microblog.user.dao.mapper")
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.cloud.microblog.user"})
public class UserWebApplication {
    public static void main(String args[]) {
        SpringApplication.run(UserWebApplication.class, args);

    }
}
