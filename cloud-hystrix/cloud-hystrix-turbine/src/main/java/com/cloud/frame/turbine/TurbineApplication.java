package com.cloud.frame.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 02:49
 **/

@EnableEurekaClient
@SpringBootApplication
@EnableTurbine
public class TurbineApplication {
    public static void main(String[] args) {

        SpringApplication.run(TurbineApplication.class,args);
    }
}
