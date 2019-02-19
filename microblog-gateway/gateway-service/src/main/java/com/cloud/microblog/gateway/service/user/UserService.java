package com.cloud.microblog.gateway.service.user;

import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.gateway.dao.model.User;

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
    Map getRsaKey();

    /**
     *功能描述
     * @author lgj
     * @Description  通过电话/邮箱登录
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    UserReturnCode login(String name,String password);

    /**
     *功能描述
     * @author lgj
     * @Description  获取当前登录的用户
     * @date 2/19/19
     * @param:
     * @return:
     *
    */
    User queryCurrentLoginInfo();

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
    boolean checkVerificationCode(String code);
    /**
     *功能描述
     * @author lgj
     * @Description   测试图片验证码是否正确
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    boolean checkImgVerificationCode(String code);

}
