package com.microblog.gateway.filter;

import com.microblog.common.token.TokenUtil;
import com.microblog.common.token.jwt.JwtUtil;
import com.microblog.gateway.auth.AuthFilterService;
import com.microblog.gateway.constants.FilterOrderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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


        log.debug("TokenFilter" );

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String token = null;
        try{
            //从请求中获取token
            token = TokenUtil.getTokenFromRequest(request);
            log.debug("token = {}",token);
            //校验Token
           if(JwtUtil.verify(token)){
               log.debug("Token 正常");
               String userId = JwtUtil.getClaim(token,"userId");
               currentContext.addZuulRequestHeader("userId",userId);

               currentContext.addZuulRequestHeader("Authorization",token);
               Map<String, String> headers =  currentContext.getZuulRequestHeaders();

               headers.forEach((key,val)->log.debug("{}--{}",key,val));
               request.setAttribute("Authorization",token);

              String tokenAttr =  (String)request.getAttribute("Authorization");

              log.debug("tokenAttr = " + tokenAttr);

               currentContext.setRequest(request);

               currentContext.addZuulRequestHeader("Authorization1",token);
               currentContext.addZuulRequestHeader("aaa",token);


           }

        }
        catch(Exception ex){
            ex.printStackTrace();
          //  RedirectUtil.redirect(currentContext,"/user/login.html");
        }
        return null;
        //token 存在 ，不做处理，在

    }


}
