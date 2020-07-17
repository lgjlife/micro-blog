package com.microblog.encry;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class HashUtil {

    public static void main(String args[]){

        byte[] password = "123456".getBytes();
        byte[] salt = "salt".getBytes();

        byte[] result = hash("MD5",password,salt);

        System.out.println("MD5算法: 长度＝"+ result.length +  "字节  " + new String(result));

        Base64Util.printByte( Base64Util.byteToBase64(result));

        System.out.println("--------------------------------------");
        result = hash("SHA",password,salt);

        System.out.println("SHA算法: 长度＝"+ result.length +  "字节  " + new String(result));

        Base64Util.printByte( Base64Util.byteToBase64(result));

    }

    public static byte[] hash(String alg ,byte[]... datas){
       try{
           MessageDigest md5 = MessageDigest.getInstance(alg);
           for(int i = 0;i < datas.length ; i++){
               md5.update(datas[i]);
           }
           return md5.digest();
       }
       catch(Exception ex){
           log.error(ex.getMessage());
       }
       return null;
    }
}
