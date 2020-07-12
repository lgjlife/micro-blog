package com.microblog.authorization.controller;

import com.microblog.util.aop.syslog.anno.PrintUrlAnno;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 *功能描述 资源服务器获取jwt解密的publickey
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
@RestController
@AllArgsConstructor
public class JwtController {


    private final KeyPair keyPair;

    @PrintUrlAnno
    // @GetMapping("/.well-known/jwks.json")
    @GetMapping("/jwt/publickey")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
