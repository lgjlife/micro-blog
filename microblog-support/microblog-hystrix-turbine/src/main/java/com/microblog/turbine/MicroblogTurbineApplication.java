package com.microblog.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 02:49
 **/

@EnableDiscoveryClient
@SpringBootApplication
@EnableTurbine  //http://localhost:8013/turbine.stream
public class MicroblogTurbineApplication {
    public static void main(String[] args) {

        SpringApplication.run(MicroblogTurbineApplication.class,args);
    }
}
