package com.microblog.buysbusiness;

import com.microblog.util.aop.syslog.EnablePrintUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.microblog.buysbusiness.feign")
@EnablePrintUrl
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BuysBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuysBusinessApplication.class, args);
    }

}
