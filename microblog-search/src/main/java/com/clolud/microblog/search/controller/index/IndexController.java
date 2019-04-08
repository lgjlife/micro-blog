package com.clolud.microblog.search.controller.index;


import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @PrintUrlAnno
    @GetMapping("/")
    public String index(){

        return "index/index";
    }
}
