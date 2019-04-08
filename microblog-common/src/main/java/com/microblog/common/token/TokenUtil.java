package com.microblog.common.token;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 *功能描述 
 * @author lgj
 * @Description  TokenUtil
 * @date 3/1/19
*/
@Slf4j
public class TokenUtil {


    /**
     *功能描述 
     * @author lgj
     * @Description  从 HttpServletRequest 中获取Token
     * @date 3/1/19
     * @param:   HttpServletRequest
     * @return:  token
     *
    */
    public  static  String getTokenFromRequest(HttpServletRequest request)  throws TokenNotFoundException{

        String token;

        if(!StringUtils.isEmpty(token = request.getHeader(AuthConstants.HEADER_TOKEN_KEY))){
            log.debug("token.length={},headerToken = {}" ,token.length(), token);
            return token;
        }
        else  if(!StringUtils.isEmpty(token = request.getParameter(AuthConstants.PARAM_TOKEN_KEY))){
            log.debug("paramToken = {}" , token);
            return token;
        }else {
            throw  new TokenNotFoundException("Token 未存在");
        }

    }
}
