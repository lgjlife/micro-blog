package com.microblog.authorization.util;

import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.security.KeyPairGenerator;


@Slf4j
public abstract class AbstractKeypairCreator {


    private KeyPairGenerator keyPairGenerator;

    public KeyPairGenerator getKeyPairGeneratorInstance(){

        if(keyPairGenerator == null){
            synchronized (AbstractKeypairCreator.class){
                if(keyPairGenerator == null){

                    try{

                        keyPairGenerator = KeyPairGenerator.getInstance(algorithm());
                        keyPairGenerator.initialize(size());
                    }
                    catch(Exception ex){
                        log.error(ex.getMessage());
                    }
                }
            }
        }

        return keyPairGenerator;

    }


    public KeyPair genKeyPair(){

        return getKeyPairGeneratorInstance().genKeyPair();
    }
    public abstract String algorithm();
    public abstract int size();
}
