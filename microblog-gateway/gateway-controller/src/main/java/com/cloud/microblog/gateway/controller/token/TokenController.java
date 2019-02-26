package com.cloud.microblog.gateway.controller.token;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {


    @PrintUrlAnno
    @RequestMapping("/check")
    public String check(){


        return  "check  token ";
    }
}
