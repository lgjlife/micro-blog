package com.microblog.gate.filter.config;

import com.microblog.gate.filter.ElapsedFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

//@Configuration
public class GatewayFilterConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        //http://localhost:6005/socket/socket/socket/hello
        return builder.routes()
                .route(r -> r.path("/socket/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(new ElapsedFilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://microblog-websocket")//("http://localhost:8991")
                        .order(0)
                        .id("websocket_route1")
                ).build();
        // @formatter:on
    }

}
