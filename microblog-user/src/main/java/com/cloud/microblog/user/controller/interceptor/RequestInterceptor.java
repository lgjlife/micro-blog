package com.cloud.microblog.user.controller.interceptor;

import lombok.extern.slf4j.Slf4j;
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
public class RequestInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug("RequestInterceptor....");

        String tokenAttr =  (String)request.getHeader("Authorization1");
        log.debug("tokenAttr = " + tokenAttr);
        String aaa = request.getHeader("aaa");
        log.debug("aaa = " + aaa);

        String userId = request.getHeader("userId");
        log.debug("userId = " + userId);

       /* Long userId = null;
        try{
            String token =  TokenUtil.getTokenFromRequest(request);

            request.setAttribute(JWTClaimsKey.userId,2);

            String id = JwtUtil.getClaim(token, JWTClaimsKey.userId);
            if(id != null){
                userId =  Long.valueOf(id);
                request.setAttribute(JWTClaimsKey.userId,2);

                log.debug("当前登录的用户id = {}",userId);
            }
        }
        catch(Exception ex){
            log.debug("ex = " + ex.getMessage());
        }*/




        return true;
    }
}
