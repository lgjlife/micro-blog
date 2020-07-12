package com.microblog.authorization.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Base64;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


/**
 *功能描述 RSA密钥创建
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
public class RsaKeypairCreator extends AbstractKeypairCreator{

    private static final String DEFAULT_ALGORITHM = "RSA";
    private static final int DEFAULT_SIZE = 2048;

    @Override
    public String algorithm() {
        return DEFAULT_ALGORITHM;
    }

    @Override
    public int size() {
        return DEFAULT_SIZE;
    }


    public static void main(String args[]) throws Exception{

        KeyPair keyPair = new RsaKeypairCreator().genKeyPair();

        PublicKey publicKey = keyPair.getPublic();

        System.out.println("-----------------------publicKey--------------------------");
        System.out.println("Algorithm = " + publicKey.getAlgorithm());

        byte[] bytData = publicKey.getEncoded();
        String format =  publicKey.getFormat();
        System.out.println("format = " + format);

        String publicKeyStr = new String(Base64.encode(publicKey.getEncoded()));
        System.out.println("publicKeyStr = " + publicKeyStr);
        System.out.println("-----------------------publicKey--------------------------");

        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("-----------------------privateKeyStr--------------------------");
        String privateKeyStr = new String(Base64.encode(privateKey.getEncoded()));
        System.out.println("privateKeyStr = " + privateKeyStr);
        System.out.println("-----------------------privateKeyStr--------------------------");

    }
}
