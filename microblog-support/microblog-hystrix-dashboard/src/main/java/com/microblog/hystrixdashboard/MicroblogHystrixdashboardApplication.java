package com.microblog.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 02:49
 **/
// 单应用 http://localhost:8012/hystrix -- > http://localhost:8302/actuator/hystrix.stream
//  聚合监控 http://localhost:8012/hystrix -- >  http://localhost:8013/turbine.stream
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
public class MicroblogHystrixdashboardApplication {
    public static void main(String[] args) {

        SpringApplication.run(MicroblogHystrixdashboardApplication.class,args);
    }
}
