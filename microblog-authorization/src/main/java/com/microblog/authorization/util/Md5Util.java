package com.microblog.authorization.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;


/**
 *功能描述 md5工具
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
public class Md5Util {


    /**
     * 生成32个字符的哈希码
     * @param text
     * @return
     */
    public static String md5(String... text) {
        MessageDigest messageDigest = null ;
        byte[] result = null;

        try{
            messageDigest = MessageDigest.getInstance("MD5");

        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

        for(int i = 0; i< text.length; i++){

            messageDigest.update(text[i].getBytes());

        }
        //result len= 16*8 = 128bits
        result = messageDigest.digest();

        return Hex.encodeHexString(result);

    }

    public static void main(String args[]){

        String password = "123456";
        String salt = "d4gfr45xc8r";

        String result = Md5Util.md5(password,salt);

        System.out.println("password = " + password
                + " salt = " + salt
                + " result = " + result
                + " result len = " + result.length() );
    }
}
