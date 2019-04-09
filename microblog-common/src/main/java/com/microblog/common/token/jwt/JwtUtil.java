package com.microblog.common.token.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *功能描述
 * @author lgj
 * @Description   JwtUtil 工具类
 * @date 3/2/19
*/
@Slf4j
public class JwtUtil {

    // 过期时间5分钟
    private static final String secret = "0x123456789";
    private static final long EXPIRE_TIME_MINUTE = 100;


    /**
     *功能描述 
     * @author lgj
     * @Description  创建Jwt
     * @date 3/2/19
     * @param:  claims 需要保存的key-val
     * @return:  String jwt
     *
    */
    public static String createJwt(Map<String,String>  claims) {

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME_MINUTE*1000*60);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder  builder = JWT.create();

        claims.forEach((key,val)->builder.withClaim(key,val));
        builder.withExpiresAt(date);
        return builder.sign(algorithm);
    }

   /**
    *功能描述 
    * @author lgj
    * @Description  
    * @date 3/2/19
    * @param: 
    * @return: 
    *
   */
    public static boolean verify(String token) throws Exception {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }

    /**
     *功能描述 
     * @author lgj
     * @Description  获取jwt中的value
     * @date 3/2/19
     * @param: 
     * @return: 
     *
    */
    public static String getClaim(String token,String key) {
        try {
            log.debug("key = {},getClaim token = {}",key, token);
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

  


    public static void main(String args[]){

        String user = "liang";
        String password = "123456";

        Map<String ,String> map = new HashMap<>();
        map.put("userId","123");
        String token = JwtUtil.createJwt(map);
        log.debug("token = {}",token);
        /*** 头信息、有效载荷、签名
         eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
         .eyJleHAiOjE1NTA4NDQxNjYsInVzZXJuYW1lIjoibGlhbmcifQ
         .marifGLxrAShTx8UHABaoc693v5j2Ai9TTUyz4jABu0
         */

        String userId = JwtUtil.getClaim(token,"userId");

        log.debug("userId = {}" , userId);

        int count  = 0;
        String tt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTE0OTkyMDYsInVzZXJuYW1lIjoibGlhbmcifQ.qbzxXYniHwczw8AsuOroFciXXt5z_AkZ1hbKWvu-4vw";
        while (true){

            try{
                boolean result = JwtUtil.verify(token);

                log.debug("verify result = {} ,time = {}s",result,count++);

                Thread.sleep(1000);

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }

    }


}
