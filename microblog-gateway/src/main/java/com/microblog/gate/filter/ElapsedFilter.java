package com.microblog.gate.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
//@Component
public class ElapsedFilter implements GatewayFilter , Ordered {

    private final  String  ElapsedTime = "ElapsedTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("DemoFilter .... ");
        exchange.getAttributes().put("ElapsedTime",System.currentTimeMillis());

       return chain.filter(exchange).then(
                Mono.fromRunnable(()->{

                    Long startTime = exchange.getAttribute(ElapsedTime);
                    if (startTime != null) {
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                }));


    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
