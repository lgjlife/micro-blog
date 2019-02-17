package com.cloud.microblog.gateway.controller.user;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {


    @PrintUrlAnno
    @GetMapping("/login")
    public String login(HttpServletRequest request){

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        log.debug("session id = " + session.getId());
        session.setAttribute("data","login value");

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            log.debug("cookie Value = " + cookie.getValue() + "  cookie name =  " + cookie.getName() + "  cookie age =  " + cookie.getMaxAge() );
        }
        return   String.valueOf(new Random().nextInt(100)) ;
    }

    @PrintUrlAnno
    @GetMapping("/logout")
    public String logout(){

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        log.debug("session id = " + session.getId());
        String value = (String) session.getAttribute("data");

        log.debug("value = " + value);

        return   String.valueOf(new Random().nextInt(100)) ;
    }

}
