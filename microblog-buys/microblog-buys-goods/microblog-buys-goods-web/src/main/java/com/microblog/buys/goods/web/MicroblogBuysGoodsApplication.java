package com.microblog.buys.goods.web;

import com.microblog.util.aop.syslog.EnablePrintUrl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.microblog.buys.goods.dao.mapper")
@EnablePrintUrl
@SpringBootApplication(scanBasePackages="com.microblog.buys.goods",exclude = DataSourceAutoConfiguration.class)
public class MicroblogBuysGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogBuysGoodsApplication.class, args);
    }

}
