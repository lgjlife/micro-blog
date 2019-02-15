package com.cloud.frame.cloudzuul.controller.oauth2;

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

    /*@RequestMapping("/")
    public String main(){
        return  "main " + new Random().nextInt(100);
    }*/

    @RequestMapping("/index1")
    public String index1(){
        return  "index  1  " + new Random().nextInt(100);
    }
    @RequestMapping("/index2")
    public String index2(){
        return  "index  2  " + new Random().nextInt(100);
    }
    /*@RequestMapping("/login")
    public String login(){
        return  "login 成功 " + new Random().nextInt(100);
    }*/


    @RequestMapping("/user1")
    public Principal user(Principal user){
        log.info("/user1");

        if(user == null){
            log.info("user is null");
        }

       return user;


    }
}
