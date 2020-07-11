package com.microblog.webflux.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class CsrfFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {

            log.info("cookie = " + cookie.getName() + " " + cookie.getValue());
        }

        Enumeration<String> names =  request.getHeaderNames();

        while (names.hasMoreElements()){
            String name = names.nextElement();
            String header = request.getHeader(name);

            log.info(name + " " + header);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
