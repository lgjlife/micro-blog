package com.microblog.user.web.interceptor;


import com.microblog.common.token.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *功能描述 
 * @author lgj
 * @Description  拦截器 ，从jwt 中获取userId
 * @date 3/2/19
*/
@Slf4j
//@Component
@ConditionalOnBean
public class RequestInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug("RequestInterceptor....");

        String token =  (String)request.getHeader("token");

        if (token != null){
            log.debug("token = " + token);
            Long uId = Long.valueOf(JwtUtil.getClaim(token,"userId"));
            log.debug("token userId = " + uId);
            request.setAttribute("userId",uId);
        }
        return true;
    }
}
