package com.microblog.websocket.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Slf4j
@Controller
@RequestMapping("/socket")
public class WebSocketController {



    @RequestMapping("/home")
    public String home(){

        log.info("/socket/home");
        return "home";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        log.info("hello...");
        try{

            Thread.sleep(50);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
        log.info("hello  return...");
        return "hello，"+ new Date().toString();
    }
}
