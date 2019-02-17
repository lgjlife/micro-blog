package com.cloud.microblog.gateway.controller.home;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @program: cloud-parent
 * @description: 主页访问
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-27 16:16
 **/

@Controller
@RequestMapping("/")
public class HomeController {

    @PrintUrlAnno(layer = "controller",description = "主页面")
    @RequestMapping
    public String loginPage(){



        System.out.println("/////////////////////////////");
        return "/home/index";
    }
}