package com.microblog.gateway.filter;


import com.microblog.common.token.TokenUtil;
import com.microblog.common.token.jwt.JwtUtil;
import com.microblog.gateway.auth.AuthFilterService;
import com.microblog.gateway.constants.FilterOrderConstants;
import com.microblog.gateway.utils.RedirectUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
//@Slf4j
public class TokenFilter  extends ZuulFilter {

    @Autowired
    AuthFilterService authFilterService;

    public  static Logger log = LoggerFactory.getLogger(TokenFilter.class);
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


        log.debug("Start TokenFilter ....." );

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String token = null;
        try{
            //从请求中获取token
            token = TokenUtil.getTokenFromRequest(request);
           //校验Token
           if(JwtUtil.verify(token)){
               log.debug("Token ={}",token);
               currentContext.addZuulRequestHeader("token",token);
           }

        }
        catch(Exception ex){
            log.debug("解析Token 出现错误，请重新登录！" + ex.getMessage());
            RedirectUtil.redirect(currentContext,"/user/login.html");
        }
        return null;
        //token 存在 ，不做处理，在

    }


}
