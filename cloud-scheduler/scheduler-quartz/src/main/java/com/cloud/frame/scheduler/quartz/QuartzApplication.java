package com.cloud.frame.scheduler.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 00:15
 **/

@MapperScan(basePackages = "com.cloud.frame.scheduler.quartz.dao.mapper")
@EnableEurekaClient
@SpringBootApplication
//使能spring 定时任务
@EnableScheduling
public class QuartzApplication {

    public static void main(String[] args) {

        SpringApplication.run(QuartzApplication.class,args);
    }
}
