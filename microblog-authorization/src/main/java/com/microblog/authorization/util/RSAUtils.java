package com.microblog.authorization.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;


/**
 *功能描述  rsa　工具类
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
public class RSAUtils {

    public static void main(String args[]){

        KeyPair keyPair = new RsaKeypairCreator().genKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();


        String origin = "123456789";
        System.out.println("原始字符串="+origin);

        RSAUtils rsaUtils = new RSAUtils();
        String encrypt = rsaUtils.encrypt(origin,publicKey);
        String result = rsaUtils.dencrypt(encrypt,privateKey);

        System.out.println("公钥加密之后: " + encrypt);
        System.out.println("私钥解密之后: " + result);

        System.out.println("--------------------------");


        String encrypt1 = rsaUtils.encrypt(origin,privateKey);
        String result1 = rsaUtils.dencrypt(encrypt1,publicKey);

        System.out.println("私钥加密之后: " + encrypt1);
        System.out.println("公钥解密之后: " + result1);


    }

    /**
     * 公钥加密
     * @param content
     * @param publicKey
     * @return
     */
    public static String encrypt(String content, PublicKey publicKey){

        try{

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] data = cipher.doFinal(content.getBytes());
            Base64.Encoder encoder  = Base64.getEncoder();
            String result = new String(encoder.encode(data));

            return result;

        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();

        }

        return null;

    }

    /**
     * 私钥加密
     * @param content
     * @param privateKey
     * @return
     */
    public static  String encrypt(String content, PrivateKey privateKey){

        try{

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,privateKey);
            byte[] data = cipher.doFinal(content.getBytes());
            Base64.Encoder encoder  = Base64.getEncoder();
            String result = new String(encoder.encode(data));

            return result;

        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

        return null;
    }

    /**
     * 公钥加密
     * @param content
     * @param publicKey
     * @return
     */
    public static  String dencrypt(String content, PublicKey publicKey){

        try{
            Base64.Decoder decoder  = Base64.getDecoder();
            byte[] decodeData = decoder.decode(content);


            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,publicKey);

            byte[] data = cipher.doFinal(decodeData);

            String result = new String(data);

            return result;

        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

        return null;

    }

    /**
     * 私钥加密
     * @param content
     * @param privateKey
     * @return
     */
    public static  String dencrypt(String content, PrivateKey privateKey){

        try{
            Base64.Decoder decoder  = Base64.getDecoder();
            byte[] decodeData = decoder.decode(content);


            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);

            byte[] data = cipher.doFinal(decodeData);

            String result = new String(data);

            return result;

        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

        return null;
    }



}
