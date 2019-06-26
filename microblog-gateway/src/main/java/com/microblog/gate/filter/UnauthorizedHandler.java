package com.microblog.gate.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 *功能描述
 * @author lgj
 * @Description  认证未通过处理
 * @date 6/26/19
*/
@Slf4j
public class UnauthorizedHandler {

    private static final  String loginPath = "/user/login.html";
    private static final String REDIRECT_URL_KEY = "redirectUrl";
    private static final String ENABLE_REDIRECT_KEY = "enableRedirect";
    /**
     *功能描述
     * @author lgj
     * @Description  跳转登录页面
     * @date 6/26/19
     * @param:
     * @return:
     *
    */
    public static Mono<Void> reDirectLoginPage( ServerWebExchange exchange){

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        ServerHttpRequest request = exchange.getRequest();

        //如果是Ajax请求
        if("XMLHttpRequest".equals(request.getHeaders().getFirst("X-Requested-With"))){
            log.debug("ajax redirect");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //设置跳转地址
            response.getHeaders().set(REDIRECT_URL_KEY, loginPath);
            //设置跳转使能
            response.getHeaders().set(ENABLE_REDIRECT_KEY,"true");

        }
        //如果是浏览器地址栏请求
        else {
            log.debug("normal redirect ");
            response.getHeaders().set("Location", loginPath);
        }
        return exchange.getResponse().setComplete();
    }
}
