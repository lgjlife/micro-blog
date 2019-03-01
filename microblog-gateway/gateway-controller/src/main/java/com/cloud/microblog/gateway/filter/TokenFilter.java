package com.cloud.microblog.gateway.filter;

import com.cloud.microblog.gateway.auth.AuthFilterService;
import com.cloud.microblog.gateway.constants.AuthConstants;
import com.cloud.microblog.gateway.constants.FilterOrderConstants;
import com.cloud.microblog.gateway.utils.RedirectUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class TokenFilter  extends ZuulFilter {

    @Autowired
    AuthFilterService authFilterService;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterOrderConstants.TOKEN_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {

       HttpServletRequest request =  RequestContext.getCurrentContext().getRequest();
       //通过路径判断是否需要验证Token
       return authFilterService.needFilter(request);
    }

    @Override
    public Object run() throws ZuulException {


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
        //token 不存在，则重定向到登录页面
        if((StringUtils.isEmpty(headerToken)) && (StringUtils.isEmpty(paramToken))){
          //  currentContext.setSendZuulResponse(false);
            RedirectUtil.redirect(currentContext,"/user/login.html");

        }
        return null;
    }


}
