package com.microblog.scheduler.web.utils;

import com.microblog.scheduler.web.exception.UserUnloginException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class UserUtil {

    public static long getUserId(HttpServletRequest request) throws UserUnloginException {

        Long userId =  (Long)request.getAttribute("userId");

        if(userId == null){
            throw  new UserUnloginException("Current not user login!");
        }
        log.debug("userId = " + userId);
        return userId;

    }
}
