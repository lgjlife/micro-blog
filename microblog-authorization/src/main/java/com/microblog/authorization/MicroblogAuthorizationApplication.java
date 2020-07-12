package com.microblog.authorization;

import com.microblog.util.aop.syslog.EnablePrintUrl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages="com.microblog.authorization.dao.mapper")
@EnablePrintUrl
@SpringBootApplication
public class MicroblogAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogAuthorizationApplication.class, args);
    }

}
