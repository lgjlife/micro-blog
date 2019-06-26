package com.microblog.gate.swagger;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class SwaggerFilter implements GatewayFilter, Ordered {

    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        log.info("path = " + path);
        if (!StringUtils.endsWithIgnoreCase(path, SwaggerProvider.API_URI)) {
            log.info("[{}] not endwith [{}]",path,SwaggerProvider.API_URI);
            return chain.filter(exchange).then(
                    Mono.fromRunnable(()->{
                        log.info("结束请求");
                    }));
        }
        String basePath = path.substring(0, path.lastIndexOf(SwaggerProvider.API_URI));
        log.info("basePath = " + basePath);
        ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        return chain.filter(newExchange);
    }

    @Override
    public int getOrder() {
        return -50;
    }
}
