package com.microblog.authorization.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

@Slf4j
public class KeypairCreator {

    private static final String DEFAULT_ALGORITHM = "RSA";
    private static final int DEFAULT_SIZE = 2048;


    public static  KeyPair create(String alg,int size){

        try{
            KeyPairGenerator keyPairGenerator  = KeyPairGenerator.getInstance(alg);
            keyPairGenerator.initialize(size);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            return keyPair;
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return null;
        }
    }

    public static  KeyPair create(){
       return create(DEFAULT_ALGORITHM,DEFAULT_SIZE);
    }

    public static void main(String args[]) throws Exception{

        KeyPair keyPair = KeypairCreator.create();

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
