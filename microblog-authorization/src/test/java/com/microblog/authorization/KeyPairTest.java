package com.microblog.authorization;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


@Slf4j
public class KeyPairTest {

    @Test
    public void keypair(){

        log.info("keypair....");

        KeyPair keyPair = keyPair1();

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

        log.info("privateKey = " + privateKey);


    }

    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("demojwt.jks"), "keystorepass".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "keypairpass".toCharArray());
    }

    public KeyPair keyPair1() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"), "fzp123".toCharArray());
        return keyStoreKeyFactory.getKeyPair("fzp-jwt", "fzp123".toCharArray());
    }

    public void createkeyPair(){

    }
}
