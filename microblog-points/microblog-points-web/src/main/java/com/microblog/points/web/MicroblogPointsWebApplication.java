package com.microblog.points.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@MapperScan("com.microblog.points.dao.mapper")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages="com.microblog.points")
public class MicroblogPointsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogPointsWebApplication.class, args);
    }

}
