package com.cloud.microblog.common.utils.encrypt.rsa;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
public class RSAUtilTest {

    @Test
    public  void encrypt() throws  Exception{
        log.debug("encrypt  test ...");

        KeyPair key = RSAKeyFactory.getInstance().getKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) key.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)key.getPrivate();

        log.debug("RSAPublicKey = " + publicKey);
        log.debug("RSAPrivateKey = " + privateKey);

        String modulus = publicKey.getModulus().toString(16);
        String exponent = publicKey.getPublicExponent().toString(16);

        RSAPublicKey privateKeyNew = RSAUtil.generateRSAPublicKey(modulus.getBytes(),exponent.getBytes());
        log.debug("由modulus，exponent 生成的 privateKeyNew = " + privateKeyNew);

        String originPasswoed = "123456zxcv";
        log.debug("原始密码 = " + originPasswoed);
        byte[] encPassword =  RSAUtil.encrypt(publicKey,originPasswoed.getBytes());

        log.debug("加密后的密码 = " + new String(encPassword) );
        log.debug("encPassword len  =  " + encPassword.length);

        byte[] en_result  = RSAUtil.hexStringToBytes(new String(encPassword));//解决Bad arguments问题
        log.debug("en_result len  =  " + en_result.length);

        byte[] decPasswordByte = RSAUtil.decrypt(privateKey,encPassword);

        String decPassqord = new String(decPasswordByte);

        log.debug("解密后的密码 = " + decPassqord);





    }
}