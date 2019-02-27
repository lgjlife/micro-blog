package com.cloud.microblog.gateway.controller;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/unauth")
public class UnauthController {

    @PrintUrlAnno
    @RequestMapping
    public  String unauth(){
        return  "redirect:/user/login.html";
    }

}
