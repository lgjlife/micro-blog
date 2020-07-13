package com.microblog.authorization;

import com.microblog.util.aop.syslog.EnablePrintUrl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableDiscoveryClient
@MapperScan(basePackages="com.microblog.authorization.dao.mapper")
@EnablePrintUrl
@SpringBootApplication
public class MicroblogAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogAuthorizationApplication.class, args);
    }

}
