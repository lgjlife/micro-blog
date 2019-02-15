package com.cloud.frame.cloudzuul.controller;

import com.cloud.frame.cloudzuul.aop.syslog.anno.PrintUrlAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Date;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-27 16:24
 **/

@Controller
@RequestMapping("/test")
public class TestController {

    @PrintUrlAnno
    @GetMapping("/1")
    @ResponseBody
    public String  test1(){

        return new Date().toString() + "   test 1";
    }

    @PrintUrlAnno
    @RequestMapping("/client")
    public String home(Principal user) {
        return "Hello " + user.getName();
    }


}
