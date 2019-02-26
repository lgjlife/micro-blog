package com.cloud.microblog.gateway.filter;

import com.cloud.microblog.gateway.constants.AuthConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

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

        System.out.println("sadasfdsfaffdssdfadfsa");

        log.debug("TokenFilter" );


        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String headerToken = request.getHeader(AuthConstants.HEADER_TOKEN_KEY);
        if(!StringUtils.isEmpty(headerToken)){
            log.debug("headerToken = {}" , headerToken);
        }

        String paramToken = request.getParameter(AuthConstants.PARAM_TOKEN_KEY);
        if(!StringUtils.isEmpty(paramToken)){
            log.debug("paramToken = {}" , paramToken);
        }
        return null;
    }
}
