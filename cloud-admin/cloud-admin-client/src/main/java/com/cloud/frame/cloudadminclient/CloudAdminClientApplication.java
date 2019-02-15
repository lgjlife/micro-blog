package com.cloud.frame.cloudadminclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.DispatcherServlet;

@EnableEurekaClient
@SpringBootApplication
public class CloudAdminClientApplication {

    public static void main(String[] args) {

        DispatcherServlet
        SpringApplication.run(CloudAdminClientApplication.class, args);
    }
}
