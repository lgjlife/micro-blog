package com.cloud.microblog.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
@Slf4j
public class PostZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
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

        log.debug("PostZuulFilter...");

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        log.debug("ContextPath:{},ServletPath:{}",request.getContextPath(),request.getServletPath());





        return null;
    }

    private void sendRedirect(HttpServletResponse response, String redirectUrl){
        try {

            log.debug("redirectUrl = {}",redirectUrl);
           // response.setHeader(HttpHeaders.LOCATION, redirectUrl);
           // response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());

            response.sendRedirect(redirectUrl);
            response.flushBuffer();


        } catch (IOException ex) {
            log.error("Could not redirect to: " + redirectUrl, ex);
        }
    }
}
