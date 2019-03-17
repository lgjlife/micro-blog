package com.cloud.microblog.blog.service.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;


@Slf4j
public class UserUtil {

    public  static   Long  getUserId(HttpServletRequest request){
        String userId = request.getHeader("userId");
        log.debug("userId = " + userId);
        return Long.valueOf(userId);
    }

}
