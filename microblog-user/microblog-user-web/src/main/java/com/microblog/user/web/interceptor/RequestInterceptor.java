package com.microblog.user.web.interceptor;


import com.microblog.common.token.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

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

        log.debug("RequestInterceptor,The ServletPath is :",request.getRequestURI());
        Enumeration<String> headerNames =  request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            System.out.println(name + "---" + request.getHeader(name));
        }


        String token =  (String)request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null){
            log.debug("token = " + token);
            String userId = JwtUtil.getClaim(token,"userId");
            if(userId != null){
                Long uId = Long.valueOf(userId);
                log.debug("token userId = " + uId);
                request.setAttribute("userId",uId);
            }

        }
        return true;
    }
}
