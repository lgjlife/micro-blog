package com.microblog.gateway.util;

import com.nimbusds.jose.Header;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

@Slf4j
public class JwkUtil {

    /**
     * jwtClaimsSet = {"user_name":"my-username",
     * "scope":["default-scope"],
     * "exp":1594299157,
     * "authorities":["ROLE_ADMIN","USER1","ROLE_USER","ADMIN1"],
     * "jti":"c4584bea-7417-498d-9089-f593c9e3e86a",
     * "client_id":"test-client"}
     *
     *
     *
     * */
    public static void decodeJWT2(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();

        headers.forEach((key, val) -> {
          //  log.info("{}---{}", key, val);
        });



        List<String> authorization = headers.get("Authorization");
        if (authorization != null) {
            String[] arr = null;
            if (authorization.size() != 0) {
                arr = authorization.get(0).split(" ");
            }
            String token = null;
            if (arr.length == 2) {
                token = arr[1];
               // log.info("token = " + token);
            }
            //request.getHeaders().set("",);
            try {
                if (token != null) {
                    JWT jwt = JWTParser.parse(token);
                    Header jwtHeader = jwt.getHeader();
                    JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();

                }
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
    }



}
