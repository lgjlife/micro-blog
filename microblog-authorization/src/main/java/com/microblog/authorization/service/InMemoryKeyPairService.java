package com.microblog.authorization.service;

import com.microblog.authorization.util.KeypairCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;


/**
 *功能描述  基于内存的密钥服务
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
@Component
public class InMemoryKeyPairService implements KeyPairService {

    private ConcurrentHashMap<String,PrivateKey> keyMap = new ConcurrentHashMap<>();


    /**
     * 创建并获取公有密钥
     * @param username
     * @return
     */
    @Override
    public String createPublickey(String username) {

        KeyPair keyPair = KeypairCreator.create();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Base64.Encoder encoder = Base64.getEncoder();
        String privateKeyStr = new String(encoder.encode(privateKey.getEncoded()));
        String publicKeyStr = new String(encoder.encode(publicKey.getEncoded()));

        log.info("publicKeyStr = " + publicKeyStr);
        log.info("privateKeyStr = " + privateKeyStr);


        keyMap.put(username,privateKey);

        return publicKeyStr;
    }

    /**
     * 获取私有密钥
     * @param username
     * @return
     */
    @Override
    public PrivateKey queryPrivatekey(String username) {

        PrivateKey privateKey = keyMap.get(username);
        return privateKey;
    }
}
