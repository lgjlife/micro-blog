package com.microblog.user.service.security;

import java.security.MessageDigest;

/**
 *功能描述 
 * @author lgj
 * @Description hash工具类
 * @date 7/20/19
*/
public class SecurityUtil {

    /*
     *功能描述 
     * @author lgj
     * @Description   md5 哈希算法
     * @date 7/20/19
     * @param:
     * source：  要进行hash的字符串
     * hashIterations 迭代次数
     * @return:  java.lang.String
     *
    */
    public static String md5Hash(String source,int hashIterations){

      return   md5Hash(source,null,hashIterations);

    }

    /*
     *功能描述
     * @author lgj
     * @Description   md5 哈希算法
     * @date 7/20/19
     * @param:
     * source：  要进行hash的字符串
     * salt:     盐
     * hashIterations 迭代次数
     * @return:  java.lang.String
     *
     */
    public static String md5Hash(String source,String salt,int hashIterations){

        StringBuilder sb = new StringBuilder() ;
        try{

            MessageDigest md5 =  MessageDigest.getInstance("MD5");
            for (int i = 0 ; i < hashIterations;i++){
                md5.update(source.getBytes("utf8"));
                if(salt != null){
                    md5.update(salt.getBytes("utf8"));
                }
            }

            byte[]  result = md5.digest();

            for(byte b:result){
                sb.append(String.format("%02X", b));
            }



        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return sb.toString();
    }

    public static String shaHash(String source,int hashIterations){

        return md5Hash(source,null,hashIterations);

    }

    public static String shaHash(String source,String salt,int hashIterations){

        StringBuilder sb = new StringBuilder() ;
        try{

            MessageDigest sha1 =  MessageDigest.getInstance("SHA-1");
            for (int i = 0 ; i < hashIterations;i++){
                sha1.update(source.getBytes("utf8"));
                if(salt != null){
                    sha1.update(salt.getBytes("utf8"));
                }
            }

            byte[]  result = sha1.digest();

            for(byte b:result){
                sb.append(String.format("%02X", b));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return sb.toString();
    }




}

