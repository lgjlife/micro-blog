package com.microblog.points.web.utils;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    public static Long  getUserId(HttpServletRequest request){
        Long userId =  (Long)request.getAttribute("userId");
        return userId;
    }
}
