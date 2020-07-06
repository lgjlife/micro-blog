package com.microblog.blog.web.controller;


import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/u")
public class BlogUserController {

    @Autowired
    HttpServletResponse response;

    @PrintUrlAnno
    @GetMapping("/{userId}")
    public BaseResult pabeInfo(@PathVariable("userId") Long userId)throws Exception {

        //response.sendRedirect("");
        log.info("userId = " + userId);
        response.sendRedirect("/blog/home.html");
        return null;
    }
}
