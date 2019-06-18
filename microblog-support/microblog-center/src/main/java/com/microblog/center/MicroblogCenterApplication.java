package com.microblog.center;

import com.microblog.center.banner.SpringBootBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class MicroblogCenterApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MicroblogCenterApplication.class);
        springApplication.setBanner(new SpringBootBanner());
        springApplication.run(args);
    }
}