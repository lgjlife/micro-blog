package com.microblog.gateway.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/socket")
public class WebSocketController {

    @RequestMapping("/home")
    public String home(){

        log.info("/socket/home");
        return "home";
    }
}
