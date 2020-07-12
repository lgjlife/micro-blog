package com.microblog.authorization.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.concurrent.ConcurrentHashMap;


/**
 *功能描述  基于内存的密钥服务，只适合单服务，如果授权服务器集群发布，应当重新实现KeyPairService，将PrivateKey存入数据库
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
@Component
public class InMemoryKeyPairService extends AbstractKeyPairService {

    private ConcurrentHashMap<String, byte[]> keyMap = new ConcurrentHashMap<>();

    @Override
    void doSavePrivateKey(String username,PrivateKey privateKey) {
        keyMap.put(username,privateKey.getEncoded());
    }

    @Override
    PrivateKey doGetPrivateKey(String username) {
        return byteToPrivatekey(keyMap.get(username));
    }
}
