package com.cloud.microblog.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class ErrorZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
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

        log.debug("ErrorZuulFilter...");

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        log.debug("ContextPath:{},ServletPath:{}",request.getContextPath(),request.getServletPath());

        Throwable exception = currentContext.getThrowable();

        log.debug("exception ={}",exception);
       /* log.debug("exception ={}",exception);
        currentContext.remove("throwable");
        try{
            String   redirectUrl = "/user/login.html";

            sendRedirect(currentContext.getResponse(),redirectUrl);
           // currentContext.setResponse(response);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }*/

        try{
            currentContext.remove("throwable");
            request.getRequestDispatcher("/unauth").forward(request,response);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


        return null;
    }



}
