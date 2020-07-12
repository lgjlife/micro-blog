package com.microblog.authorization.keypair;

import com.microblog.authorization.util.RsaKeypairCreator;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;


@Slf4j
public abstract class AbstractKeyPairService implements KeyPairService{

    private KeyFactory keyFactory;
    private RsaKeypairCreator keypairCreator = new RsaKeypairCreator();

    @Override
    public String createPublickey(String username) {

        KeyPair keyPair = keypairCreator.genKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Base64.Encoder encoder = Base64.getEncoder();
        String privateKeyStr = new String(encoder.encode(privateKey.getEncoded()));
        String publicKeyStr = new String(encoder.encode(publicKey.getEncoded()));

        //392
        log.info("publicKeyStr len= {}     {}" ,publicKeyStr.length(),publicKeyStr);
        //1624
        log.info("privateKeyStr len= {}    {}", privateKeyStr.length(), privateKeyStr);

        doSavePrivateKey(username,privateKey);

        return publicKeyStr;
    }

    @Override
    public PrivateKey queryPrivatekey(String username) {
        return doGetPrivateKey(username);
    }

    public KeyFactory getKeyFactoryInstance(){

        if(keyFactory == null){
            synchronized (AbstractKeyPairService.class){
                if(keyFactory == null){

                    try{

                        keyFactory  = KeyFactory.getInstance("RSA");
                    }
                    catch(Exception ex){
                        log.error(ex.getMessage());
                    }
                }
            }
        }

        return keyFactory;

    }
    public PrivateKey byteToPrivatekey(byte[] privateKeyByte){
        PrivateKey privateKey = null;
        try{
            privateKey = getKeyFactoryInstance().generatePrivate(new PKCS8EncodedKeySpec(privateKeyByte));
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

        return privateKey;

    }


    abstract void doSavePrivateKey(String username,PrivateKey privateKey);
    abstract PrivateKey doGetPrivateKey(String username);
}
