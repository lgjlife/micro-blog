package com.cloud.microblog.gateway.filter;

import com.cloud.microblog.common.token.TokenUtil;
import com.cloud.microblog.common.token.jwt.JwtUtil;
import com.cloud.microblog.gateway.auth.AuthFilterService;
import com.cloud.microblog.gateway.constants.FilterOrderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
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

        String token = null;
        try{
            //从请求中获取token
            token = TokenUtil.getTokenFromRequest(request);
            log.debug("token = {}",token);
            //校验Token
           log.debug("JwtUtil.verify = {}",JwtUtil.verify(token));

        }
        catch(Exception ex){
            ex.printStackTrace();
          //  RedirectUtil.redirect(currentContext,"/user/login.html");
        }
        return null;
        //token 存在 ，不做处理，在

    }


}
