package com.cloud.frame.gateway.controller;

import com.cloud.frame.common.aop.syslog.anno.PrintUrlAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Random;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-11 04:16
 **/

@RestController
public class MainController {

    private  static Logger  log = LoggerFactory.getLogger(MainController.class);

 /*   @RequestMapping("/")
    public String main(){
        return  "main " + new Random().nextInt(100);
    }*/

    @PrintUrlAnno
    @RequestMapping("/index")
    public String index(){
        return  "index " + new Random().nextInt(100);
    }

    /*@RequestMapping("/login")
    public String login(){
        return  "login 成功 " + new Random().nextInt(100);
    }*/

    @PrintUrlAnno
    @RequestMapping("/user1")
    public Principal user(Principal user){
        log.info("/user1");

        if(user == null){
            log.info("user is null");
        }

       return user;


    }
}
