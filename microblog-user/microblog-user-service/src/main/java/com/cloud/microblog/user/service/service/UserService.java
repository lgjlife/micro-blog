package com.cloud.microblog.user.service.service;

import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.common.result.BaseResult;

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
    boolean sendPhoneVerificationCode(String phone);
    /**
     *功能描述
     * @author lgj
     * @Description  发送验证码到邮箱
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    boolean sendEmailVerificationCode(String email);

    /**
     *功能描述
     * @author lgj
     * @Description   获取RSA 的 modulus   exponent
     * @date 2/18/19
     * @param:
     * @return: Map
     *
     */
    Map getRsaKey(String name);

    /**
     *功能描述
     * @author lgj
     * @Description  通过电话/邮箱登录
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    BaseResult login(String name, String password);



    /**
     *功能描述
     * @author lgj
     * @Description   退出登录
     * @date 2/19/19
     * @param:
     * @return:
     *
    */
    UserReturnCode logout();
    /**
     *功能描述
     * @author lgj
     * @Description  通过电话/邮箱注册
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    UserReturnCode register(String name, String password);
    /**
     *功能描述
     * @author lgj
     * @Description  测试手机邮箱验证码是否正确
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    boolean checkVerificationCode(String name, String code);
    /**
     *功能描述
     * @author lgj
     * @Description   测试图片验证码是否正确
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    boolean checkImgVerificationCode(String name, String code);

}
