package com.cloud.microblog.gateway.filter;

import com.cloud.microblog.gateway.constants.AuthConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class TokenFilter  extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {


        log.debug("TokenFilter" );


        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        String headerToken = request.getHeader(AuthConstants.HEADER_TOKEN_KEY);
        if(!StringUtils.isEmpty(headerToken)){
            log.debug("headerToken = {}" , headerToken);
        }

        String paramToken = request.getParameter(AuthConstants.PARAM_TOKEN_KEY);
        if(!StringUtils.isEmpty(paramToken)){
            log.debug("paramToken = {}" , paramToken);
        }

        if((StringUtils.isEmpty(headerToken)) && (StringUtils.isEmpty(paramToken))){
           /* throw  new UnAuthException(AuthExceptionEnum.TOKEN_EMPTY.getMessage(),
                    AuthExceptionEnum.TOKEN_EMPTY.getCode(),
                    AuthExceptionEnum.TOKEN_EMPTY.getMessage());*/
            currentContext.setSendZuulResponse(false);
            try{
            //    currentContext.remove("throwable");
             //   request.getRequestDispatcher("/unauth").forward(request,response);

                try{
                    String   redirectUrl = "/user/login.html";
                   String ajax =  request.getHeader("X-Requested-With");

                    log.debug("X-Requested-With = " + ajax);
                    sendRedirect(currentContext.getResponse(),redirectUrl);
                    // currentContext.setResponse(response);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }


        return null;
    }

    private void sendRedirect(HttpServletResponse response, String redirectUrl){
        try {

            log.debug("redirectUrl = {}",redirectUrl);
            response.setHeader("redirectUrl", redirectUrl);
            response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
            response.flushBuffer();
        } catch (IOException ex) {
            log.error("Could not redirect to: " + redirectUrl, ex);
        }
    }
}
