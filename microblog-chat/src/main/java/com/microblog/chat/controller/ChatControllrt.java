package com.microblog.chat.controller;

import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatControllrt {

    @PrintUrlAnno
    @RequestMapping("/needFilter")
    public String needFilter(){
        return  "needFilter";
    }
    @PrintUrlAnno
    @RequestMapping("/notNeedFilter")
    public String notNeedFilter(){
        return  "notNeedFilter";
    }
}
