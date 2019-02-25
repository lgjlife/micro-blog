package com.cloud.microblog.gateway.controller.user;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @PrintUrlAnno
    @RequestMapping("/test")
    public  String  redirect(){

        return "redirect:/user/login.html";
    }
}
