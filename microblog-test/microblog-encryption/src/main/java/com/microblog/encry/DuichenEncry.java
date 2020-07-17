package com.microblog.encry;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


@Slf4j
public class DuichenEncry {

    public static void main(String args[]){

        String[] alg = {"DES","AES"};
        String password = "123456";

        for(int i = 0; i< alg.length; i++){
            test(alg[i],password);
        }
    }

    public static void test(String alg,String password){
        System.out.println("--------------------------------");
        System.out.println("算法=" + alg + "  " + "原始密码:" + password);

        try{

            Cipher cipher = Cipher.getInstance(alg);

            SecretKey secretKey = KeyGenerator.getInstance(alg).generateKey();

            System.out.println("正在加密....");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] encryData = cipher.doFinal(password.getBytes());

            System.out.println("加密后的长度:" + encryData.length + "字节," + "  内容:" + new String(encryData));
            System.out.println(new String(encryData));


            System.out.println("正在解密....");
            cipher.init(Cipher.DECRYPT_MODE,secretKey);

            byte[] dencryData = cipher.doFinal(encryData);
            System.out.println("解密后的内容: " + new String(dencryData));


        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
    }

    public static byte[] encry(String alg , String password){

       try{

           Cipher cipher = Cipher.getInstance(alg);

           SecretKey secretKey = KeyGenerator.getInstance(alg).generateKey();

           cipher.init(Cipher.ENCRYPT_MODE,secretKey);

          return cipher.doFinal(password.getBytes());

       }
       catch(Exception ex){
           log.error(ex.getMessage());
       }

       return null;
    }

    public static byte[] dencry(String alg , byte[] data){

        try{

            Cipher cipher = Cipher.getInstance(alg);

            SecretKey secretKey = KeyGenerator.getInstance(alg).generateKey();

            cipher.init(Cipher.DECRYPT_MODE,secretKey);

            return cipher.doFinal(data);

        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

        return null;
    }

}
