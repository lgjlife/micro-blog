package com.microblog.blog.service.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;


@Slf4j
public class UserUtil {

    public  static   Long  getUserId(HttpServletRequest request){
        Long userId =  (Long)request.getAttribute("userId");
        log.debug("userId = " + userId);
        return userId;
    }

}
