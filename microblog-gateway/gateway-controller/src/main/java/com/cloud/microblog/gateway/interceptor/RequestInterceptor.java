package com.cloud.microblog.gateway.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
//@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug("RequestInterceptor....");

        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            log.debug("cookies is null");
            return  true;
        }
        for(Cookie cookie:cookies){
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            log.debug("SessionId={}",request.getRequestedSessionId());
            log.debug("cookie={} : {}",cookie.getName(),cookie.getValue());

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        return true;
    }
}
