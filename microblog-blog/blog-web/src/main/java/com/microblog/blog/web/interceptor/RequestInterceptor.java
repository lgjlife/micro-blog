package com.microblog.blog.web.interceptor;

import com.microblog.common.token.jwt.JwtUtil;
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

        String token =  (String)request.getHeader("token");

        if (token != null){
            log.debug("token = " + token);
            Long uId = Long.valueOf(JwtUtil.getClaim(token,"userId"));
            log.debug("token userId = " + uId);
            request.setAttribute("userId",uId);
            request.setAttribute("nickName",JwtUtil.getClaim(token,"nickName"));
        }

       /* Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()){
            log.debug("HeaderNames = " + names.nextElement());
        }*/




      /*  String Authorization =  (String)request.getHeader("authorization");
        log.debug("Authorization = " + Authorization);

        String Authorization1 =  (String)request.getHeader("authorization1");
        log.debug("Authorization1 = " + Authorization1);
        String aaa = request.getHeader("aaa");
        log.debug("aaa = " + aaa);

        String userId = request.getHeader("userId");
        log.debug("userId = " + userId);


*/


        return true;
    }
}
