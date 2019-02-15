package com.cloud.frame.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-12 21:04
 **/


@EnableEurekaClient
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProviderApplication.class,args);
    }
}
