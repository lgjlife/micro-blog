package com.cloud.microblog.gateway.service.user;


import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.common.result.BaseResult;
import com.cloud.microblog.common.utils.SessionUtils;
import com.cloud.microblog.common.utils.encrypt.rsa.RSAKeyFactory;
import com.cloud.microblog.common.utils.encrypt.rsa.RSAUtil;
import com.cloud.microblog.common.utils.sms.SmsUtil;
import com.cloud.microblog.gateway.dao.mapper.UserMapper;
import com.cloud.microblog.gateway.dao.model.User;
import com.cloud.microblog.gateway.service.utils.UserSessionKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *功能描述 
 * @author lgj
 * @Description  用户模块实现
 * @date 2/19/19
*/
@Slf4j
@Service
public class IUserService  implements  UserService{

    @Autowired
    UserMapper userMapper;
    /**
     *功能描述
     * @author lgj
     * @Description   发送验证码到电话
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    @Override
    public boolean sendPhoneVerificationCode(String phone) {
        String code = getCode();
        try{
            SmsUtil.sendSms(phone,code);
            SessionUtils.set(UserSessionKeyUtil.PHONE_VERIFICATION_CODE_KEY,code);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  true;


    }

    /**
     *功能描述
     * @author lgj
     * @Description   发送验证码到邮箱
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    @Override
    public boolean sendEmailVerificationCode(String email) {
        String code = getCode();
        try{
            SmsUtil.sendSms(email,code);
            SessionUtils.set(UserSessionKeyUtil.PHONE_VERIFICATION_CODE_KEY,code);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  true;

    }

    /**
     *功能描述
     * @author lgj
     * @Description   获取RSA 的 modulus   exponent
     * @date 2/18/19
     * @param:
     * @return: Map
     *
    */
    @Override
    public Map getRsaKey() {

        KeyPair key = RSAKeyFactory.getInstance().getKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) key.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)key.getPrivate();
        String modulus = publicKey.getModulus().toString(16);
        String exponent = publicKey.getPublicExponent().toString(16);


        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        log.debug("set key ");
        session.setAttribute("RSA:KeyPair",key);

        Map map = new HashMap();
        map.put("modulus",modulus);
        map.put("exponent",exponent);

        return map;
    }



    /**
     *功能描述
     * @author lgj
     * @Description  通过电话/邮箱登录
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    @Override
    public BaseResult login(String name, String type, String password) {
        return null;
    }

    /**
     *功能描述
     * @author lgj
     * @Description 退出登录
     * @date 2/19/19
     * @param:
     * @return:
     *
    */
    @Override
    public void logout() {

    }

    /**
     *功能描述
     * @author lgj
     * @Description  通过电话/邮箱注册
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    @Override
    public UserReturnCode register(String name, String type, String encPassword) {

        User user = null;


        //检测是否已经注册
        if("phone".equals(type)){
            if(userMapper.selectIdByPhone(name) != 0){
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setPhoneNum(name);

        }
        else if("email".equals(type)){
            if(userMapper.selectIdByEmail(name) != 0){
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setEmail(name);
        }

        //获取原始密码
        KeyPair keyPair = (KeyPair) SessionUtils.get(UserSessionKeyUtil.REGISTER_AES_KEYPAIR_KEY);
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        String password = decPassword(encPassword,privateKey);

        //对原始密码加密
        //生成盐（部分，需要存入数据库中）
        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String resultPassword = new Md5Hash(password,random,3).toString();
        log.debug("进行MD5加密的密码 = " + resultPassword);


        user.setLoginPassword(password);

        if(userMapper.insert(user) != 0){
            return UserReturnCode.REGISTER_SUCCESS;
        }

        return UserReturnCode.REGISTER_FAIL;

    }

    /**
     *功能描述
     * @author lgj
     * @Description  测试手机邮箱验证码是否正确
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    public boolean checkVerificationCode(String code){

        return  false;
    }
    /**
     *功能描述
     * @author lgj
     * @Description   测试图片验证码是否正确
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    public boolean checkImgVerificationCode(String code){
        return false;
    }

    private String decPassword(String encPassword,RSAPrivateKey privateKey ){

        String decPassqord = null;

        log.debug("开始进行解密");



        byte[] en_result  = RSAUtil.hexStringToBytes(encPassword);//解决Bad arguments问题
        log.debug("en_result len  =  " + en_result.length);

        try{
            byte[] decPasswordByte = RSAUtil.decrypt(privateKey,en_result);

            String passStr = new String(decPasswordByte);

            //翻转字符串
            StringBuffer StrBuf = new StringBuffer();
            StrBuf.append(passStr);
            decPassqord = StrBuf.reverse().toString();

            log.debug("解密后的密码 = " + decPassqord);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return decPassqord;

    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取6位数字随机码
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    private  String getCode(){

        return String.valueOf(new Random().nextInt(999999));
    }

}
