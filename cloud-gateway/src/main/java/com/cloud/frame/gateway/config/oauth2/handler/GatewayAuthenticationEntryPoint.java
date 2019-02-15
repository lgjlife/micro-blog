package com.cloud.frame.gateway.config.oauth2.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-18 04:18
 **/

@Slf4j
@Component
public class GatewayAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.debug("授权失败，禁止访问");
     //   httpServletRequest.setCharacterEncoding("UTF-8");

     ///   PrintWriter printWriter = httpServletResponse.getWriter();
     //   httpServletResponse.sendRedirect("http://localhost:6003/zuul/oauth2-server/");
     //   printWriter.append("授权失败，禁止访问");
    }
}
