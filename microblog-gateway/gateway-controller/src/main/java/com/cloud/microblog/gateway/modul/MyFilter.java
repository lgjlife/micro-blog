package com.cloud.microblog.gateway.modul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-17 22:58
 **/

//@Component
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

        log.debug("ZuulFilter....");
        HttpServletRequest req = (HttpServletRequest)RequestContext.getCurrentContext().getRequest();
        Cookie[] cookies = req.getCookies();

        for(Cookie cookie:cookies){
            System.out.println("++++++++++++++++++++++");
            log.debug("cookie={}"+cookie.getValue());
            System.out.println("++++++++++++++++++");
        }

        return null;
    }
}
