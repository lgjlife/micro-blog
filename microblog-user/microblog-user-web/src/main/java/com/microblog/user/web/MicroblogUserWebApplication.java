package com.microblog.user.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/****
 求分析 1.1. 仿微博系统分为四大模块 个人主页模块、
 微博动态模块、微博热点模块、管理员模块。
 2 系统设计 2.1. 个人主页模块的功能设计 好友页面、关注功能、私聊功能、点赞
 、取赞、转发、评论、回复功能、删除评论、删除回复、删除微博动态。
 2.2. 微博动态模块的功能设计 推荐功能、热门话题、@功能、点赞、转发、评论、回复功能、消息通知计数功能、删除评论、删除回
 *
*/

@EnableSwagger2
@EnableFeignClients(basePackages = "com.microblog.user.service.feign")
@MapperScan("com.microblog.user.dao.mapper")
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.microblog.user","com.microblog.common"})

public class MicroblogUserWebApplication {
    public static void main(String args[]) {
        SpringApplication.run(MicroblogUserWebApplication.class, args);
    }
}
