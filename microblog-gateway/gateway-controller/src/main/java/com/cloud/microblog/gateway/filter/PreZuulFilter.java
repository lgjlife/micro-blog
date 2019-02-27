package com.cloud.microblog.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
@Slf4j
public class PreZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        log.debug("PreZuulFilter...");

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            log.debug("cookies is null");
        }

        else{
            for(Cookie cookie : cookies){
                log.debug("cookie = " + cookie.getValue());
            }
        }


        log.debug("ContextPath:{},ServletPath:{}",request.getContextPath(),request.getServletPath());
        if(true){
            throw  new NullPointerException();
        }
        return null;
    }
}
