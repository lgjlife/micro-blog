package com.cloud.frame.cloudzuul.controller.login;

import com.cloud.frame.cloudzuul.aop.syslog.anno.PrintUrlAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: cloud-parent
 * @description: 登录处理
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-27 15:59
 **/

@Controller
@RequestMapping("/login")
public class LoginController {

    @PrintUrlAnno(layer = "controller",description = "跳转登录页面")
    @RequestMapping
    public String loginPage(){

        return "/login/login";
    }
}
