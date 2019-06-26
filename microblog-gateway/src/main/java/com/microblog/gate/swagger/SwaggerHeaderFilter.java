package com.microblog.gate.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

@Slf4j
//@Component
public class SwaggerHeaderFilter  extends AbstractGatewayFilterFactory {
    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    @Autowired
    SwaggerFilter swaggerFilter;


    @Override
    public GatewayFilter apply(Object config) {

        log.debug("SwaggerHeaderFilter ..");

        return swaggerFilter;



        /*return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            log.info("path = " + path);
            if (!StringUtils.endsWithIgnoreCase(path, SwaggerProvider.API_URI)) {
                log.info("[{}] not endwith [{}]",path,SwaggerProvider.API_URI);
                return chain.filter(exchange);
            }
            String basePath = path.substring(0, path.lastIndexOf(SwaggerProvider.API_URI));
            log.info("basePath = " + basePath);
            ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };*/
    }



}
