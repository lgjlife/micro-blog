package com.cloud.microblog.gateway.service.user;

import java.util.Map;

/**
 *功能描述 
 * @author lgj
 * @Description  用户相关接口
 * @date 2/18/19
*/
public interface UserService {

    /**
     *功能描述
     * @author lgj
     * @Description   发送验证码到电话
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    void sendPhoneVerificationCode(String code);
    /**
     *功能描述
     * @author lgj
     * @Description  发送验证码到邮箱
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    void sendEmailVerificationCode(String code);

    /**
     *功能描述
     * @author lgj
     * @Description   获取RSA 的 modulus   exponent
     * @date 2/18/19
     * @param:
     * @return: Map
     *
     */
    Map getRsaKey();
}
