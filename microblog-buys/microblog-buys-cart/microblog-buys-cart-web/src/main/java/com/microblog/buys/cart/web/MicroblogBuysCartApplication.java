package com.microblog.buys.cart.web;

import com.microblog.util.aop.syslog.EnablePrintUrl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.microblog.buys.cart.dao.mapper")
@EnablePrintUrl
@SpringBootApplication(scanBasePackages="com.microblog.buys.cart",exclude = DataSourceAutoConfiguration.class)
public class MicroblogBuysCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogBuysCartApplication.class, args);
    }

}
