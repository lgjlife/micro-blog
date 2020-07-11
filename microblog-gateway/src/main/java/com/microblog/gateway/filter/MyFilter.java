package com.microblog.gateway.filter;

import com.microblog.gateway.util.JwkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class MyFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

       // log.info("MyFilter ...");


        JwkUtil.decodeJWT2(serverWebExchange.getRequest());

        return webFilterChain.filter(serverWebExchange);

    }
}
