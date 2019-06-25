package com.microblog.gate.filter;

import com.microblog.common.token.jwt.JwtUtil;
import com.microblog.gate.filter.auth.AuthFilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    AuthFilterService authFilterService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("TokenFilter filter...");
        ServerHttpRequest request = exchange.getRequest();
        System.out.println("请求路径："+request.getURI());

        HttpHeaders httpHeaders = request.getHeaders();
        List<String> headers =  httpHeaders.get(HttpHeaders.USER_AGENT);

        httpHeaders.forEach((key,values)->{
            System.out.println(key + "::::" + values);
        });
        System.out.println();


        String path  = request.getURI().getPath() ;

        if(authFilterService.needFilter(path)){
            //需要拦截
            log.info("Path[{}] need filter!",path);

            String token = null;
            try{

                token = request.getHeaders().getFirst("Authorization");
                if(JwtUtil.verify(token)) {
                    log.debug("Token ={}", token);
                }
            }
            catch(Exception ex){
                log.debug("解析Token 出现错误，请重新登录！" + ex.getMessage());
              //  RedirectUtil.redirect(currentContext,"/user/login.html");
                return fallBack("/user/login.html",exchange);

            }
        }
        else {


        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> fallBack(String url,ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location", url);
        return exchange.getResponse().setComplete();
    }
}
