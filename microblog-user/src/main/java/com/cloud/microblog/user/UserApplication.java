package com.cloud.microblog.user;


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
@ComponentScan(basePackages = {"com.cloud.microblog.user.*","com.cloud.microblog.common"})
public class UserApplication {
    public static void main(String args[]) {
        SpringApplication.run(UserApplication.class, args);

    }
}
