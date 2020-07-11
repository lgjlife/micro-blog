package com.microblog.authorization.service;

import java.security.PrivateKey;

/**
 *功能描述  密钥服务
 * @author lgj
 * @Description 　　　
 * @date 　
*/
public interface KeyPairService {

    String createPublickey(String username);

    PrivateKey queryPrivatekey(String username);

}
