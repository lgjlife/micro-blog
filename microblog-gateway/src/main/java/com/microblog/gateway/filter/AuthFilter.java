package com.microblog.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        decodeJWT(request);
        return chain.filter(exchange);
    }


    private void decodeJWT(ServerHttpRequest request) {
//        HttpHeaders headers = request.getHeaders();
//
//        headers.forEach((key, val) -> {
//            log.info("{}---{}", key, val);
//        });
//
//        List<String> authorization = headers.get("Authorization");
//        if (authorization != null) {
//            String[] arr = null;
//            if (authorization.size() != 0) {
//                arr = authorization.get(0).split(" ");
//            }
//            String token = null;
//            if (arr.length == 2) {
//                token = arr[1];
//                log.info("token = " + token);
//            }
//            //request.getHeaders().set("",);
//            try {
//                if (token != null) {
//                    JWT jwt = JWTParser.parse(token);
//                    Header jwtHeader = jwt.getHeader();
//                    JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
//                    log.info("jwtClaimsSet = " + jwtClaimsSet.toString());
//                    log.info("jwtHeader = " + jwtHeader);
//                }
//            } catch (Exception ex) {
//                log.error(ex.getMessage());
//            }
//        }
    }

    @Override
    public int getOrder() {
        return 1234;
    }
}
