package com.cloud.frame.gateway.modul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-17 22:58
 **/

@Component
public class MyFilter  extends ZuulFilter {

    private  static Logger log = LoggerFactory.getLogger(MyFilter.class);

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

        log.debug("执行 MyFilter");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        Principal user = request.getUserPrincipal();
        if(user == null){
            log.debug("user is null");
        }
        else {
            log.debug("user = " + user.getName());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            log.debug(" authentication  is null");
        }
        else{
            log.debug("authentication name = " + authentication.getName());
        }



        return null;
    }
}
