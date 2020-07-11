package com.microblog.authorization.controller;

import com.microblog.authorization.service.KeyPairService;
import com.microblog.util.aop.syslog.anno.PrintUrlAnno;
import com.microblog.util.response.ResponseCode;
import com.microblog.util.response.ServerResponseDto;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * Created by Steven on 2019/10/27.
 */

@Slf4j
@RestController
@AllArgsConstructor
public class MvcController {




    private final KeyPair keyPair;

    @PrintUrlAnno
    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getKey() {
        log.info("getKey...................................");
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }




}
