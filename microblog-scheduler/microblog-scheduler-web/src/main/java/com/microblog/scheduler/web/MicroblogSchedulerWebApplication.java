package com.microblog.scheduler.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableDiscoveryClient
@MapperScan(basePackages = "com.microblog.scheduler.dao.mapper")
@SpringBootApplication
@ComponentScan(basePackages = "com.microblog.*")
public class MicroblogSchedulerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogSchedulerWebApplication.class, args);
    }

}
