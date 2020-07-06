package com.microblog.gate.auth.config;

import com.nimbusds.jose.Header;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AuthenticationConverter extends ServerFormLoginAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {

        log.info("AuthenticationConverter ");

        //从session中获取登陆用户信息
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        //获取权限信息
        JWTClaimsSet jwtClaimsSet = getJWTClaimsSet(exchange.getRequest());
        List<String> authorities = ( List<String>)jwtClaimsSet.getClaim("authorities");

        log.info("authorities = " + authorities);
        for(String authoritie:authorities){
            SimpleGrantedAuthority auth = new SimpleGrantedAuthority(authoritie);
            simpleGrantedAuthorities.add(auth);
        }

        String name = (String) jwtClaimsSet.getClaim("user_name");

        //添加用户信息到spring security之中。
        AccountAuthentication  xinyueAccountAuthentication = new AccountAuthentication(null, name, simpleGrantedAuthorities);
        return Mono.just(xinyueAccountAuthentication);

    }

    //jwtClaimsSet = {"user_name":"my-username","scope":["default-scope"],"exp":1593999547,
    // "authorities":["ROLE_ADMIN","USER1","ROLE_USER","ADMIN1"],
    // "jti":"a1e7cd89-a81b-4691-aca9-08edc9e54453","client_id":"test-client"}
    private JWTClaimsSet getJWTClaimsSet(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();

        headers.forEach((key, val) -> {
            log.info("{}---{}", key, val);
        });

        List<String> authorization = headers.get("Authorization");
        if (authorization != null) {
            String[] arr = null;
            String token = null;
            String allToken = authorization.get(0);
            if(allToken.startsWith("Bearer ")){
                token = allToken.substring(7,allToken.length());
            }
            if (authorization.size() != 0) {
                arr = authorization.get(0).split(" ");
            }
            try {
                JWT jwt = JWTParser.parse(token);
                Header jwtHeader = jwt.getHeader();
                JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
                log.info("jwtClaimsSet = " + jwtClaimsSet.toString());
                log.info("jwtHeader = " + jwtHeader);

                return jwtClaimsSet;
            } catch (Exception ex) {


                ex.printStackTrace();
            }
        }

        return null;
    }
}
