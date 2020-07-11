package com.microblog.authorization.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

@Slf4j
public class Md5Util {

    public static String md5(String text) {

        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(text.getBytes());
            return Hex.encodeHexString(bytes);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

        return null;

    }
}
