package com.cloud.microblog.gateway.service.user;


import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.common.utils.SessionUtils;
import com.cloud.microblog.common.utils.UserRegexUtil;
import com.cloud.microblog.common.utils.encrypt.rsa.RSAKeyFactory;
import com.cloud.microblog.common.utils.encrypt.rsa.RSAUtil;
import com.cloud.microblog.common.utils.mail.MailSenderMsg;
import com.cloud.microblog.common.utils.mail.MailService;
import com.cloud.microblog.gateway.dao.mapper.UserMapper;
import com.cloud.microblog.gateway.dao.model.User;
import com.cloud.microblog.gateway.service.utils.UserSessionKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
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

    boolean flag = false;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MailService mailService;
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
            // TODO  使用消息中间件处理
            Date start = new Date();
         //   SmsUtil.sendSms(phone,code);
            Date end = new Date();
            log.debug("向手机号({})发送验证码:({})",phone,code);
            log.info("短信花费时间：" + (end.getTime() - start.getTime()));
            SessionUtils.set(UserSessionKeyUtil.PHONE_EMAIL_VERIFICATION_CODE_KEY,code);
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
            String content = "本次验证码为：" + code+"." ;
            Date start = new Date();
            // TODO  使用消息中间件处理
            MailSenderMsg msg = new MailSenderMsg("563739007@qq.com","Micro-Blog 验证码",content);
            mailService.sendSimpleMail(msg);
            log.debug("向邮箱({})发送验证码:({})",email,code);
            Date end = new Date();
            log.info("邮件花费时间：" + (end.getTime() - start.getTime()));
            SessionUtils.set(UserSessionKeyUtil.PHONE_EMAIL_VERIFICATION_CODE_KEY,code);
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

        KeyPair keyPair = RSAKeyFactory.getInstance().getKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        String modulus = publicKey.getModulus().toString(16);
        String exponent = publicKey.getPublicExponent().toString(16);

        SessionUtils.set(UserSessionKeyUtil.REGISTER_AES_KEYPAIR_KEY,keyPair);

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
    public UserReturnCode login(String name,  String encPassword) {

        User user = null;
        //
        //获取原始密码
        KeyPair keyPair = (KeyPair) SessionUtils.get(UserSessionKeyUtil.REGISTER_AES_KEYPAIR_KEY);
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        String password = decPassword(encPassword,privateKey);
        log.debug("原始密码 = " + password);

        Subject subject = null;
        try {

            //开始认证
            log.debug("开始认证");
            subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(name,password);
            log.debug("认证状态 = " + subject.isAuthenticated());

            token.setRememberMe(true);
            /*登录验证*/
            subject.login(token);
            log.debug("用户{}登录成功",name);
            if(UserRegexUtil.isMobile(name)){
                user = userMapper.selectByPhone(name);
            }
            else if(UserRegexUtil.isEmail(name)){
                user = userMapper.selectByEmail(name);
            }

            SessionUtils.set(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY,user);


        }catch(UnknownAccountException uae){
            log.debug("对用户[" + name + "]进行登录验证..验证未通过,未知账户");
            return UserReturnCode.LOGIN_UNKNOW_ACCOUT;
        }catch(IncorrectCredentialsException ice){
            log.debug("对用户[" + name + "]进行登录验证..验证未通过,错误的凭证");
            return UserReturnCode.LOGIN_PASSWORD_ERR;
        }catch(LockedAccountException lae){
            log.debug("对用户[" + name + "]进行登录验证..验证未通过,账户已锁定");
            return UserReturnCode.LOGIN_LOCK_ACCOUNT;
        }catch(ExcessiveAttemptsException eae){
            log.debug("对用户[" + name + "]进行登录验证..验证未通过,错误次数过多");
            return UserReturnCode.LOGIN_PASSWORD_ERR_MORE;
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.debug("对用户[" + name + "]进行登录验证..验证未通过,堆栈轨迹如下5");
            return UserReturnCode.LOGIN_FAIL;
         }
        log.debug("认证状态 = " + subject.isAuthenticated());
        if(subject.isAuthenticated()) {
            log.debug("用户[" + name + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        }
        return UserReturnCode.LOGIN_SUCCESS;
    }

    @Override
    public User queryCurrentLoginInfo() {
        User user = (User) SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);

        if(flag == false){
            flag = true;
            new Thread(){
                User user1 ;
                @Override
                public void run() {
                    while(true){
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                        user1 = (User) SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
                        if(user1 != null){

                            log.debug("用户信息 = " + user1);
                            Subject subject = SecurityUtils.getSubject();
                            Session session = subject.getSession();
                            log.debug("session -d ={}",session.getId());
                        }
                        else {
                            log.debug("用户信息 -- null  ");
                        }
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                        try{
                            Thread.sleep(5*1000);
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }


                }
            }.start();
        }


        if(user != null){
            log.debug("user info = " + user);
        }
        else {
            log.debug("user is null  ");
        }
        return  user;
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
    public UserReturnCode logout() {

        User user = (User)SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
        log.debug("用户{}退出登录",user.getPhoneNum());
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return  UserReturnCode.LOGOUT_SUCCESS;
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
    public UserReturnCode register(String name,String encPassword) {

        User user = null;
        log.debug("{}正在注册.....",name);

        //检测是否已经注册
        if(UserRegexUtil.isMobile(name)){
            if(userMapper.selectIdByPhone(name) != null){
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setPhoneNum(name);
            log.debug("手机帐号({})注册",name);

        }
        else if(UserRegexUtil.isEmail(name)){
            if(userMapper.selectIdByEmail(name) != null){
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setEmail(name);
            log.debug("手机帐号({})注册",name);
        }
        log.debug(user.toString());

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


        user.setLoginPassword(resultPassword);
        user.setSalt(random);
        user.setRegisterTime(new Date());

        log.debug(user.toString());

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

        String saveCode = (String)SessionUtils.get(UserSessionKeyUtil.PHONE_EMAIL_VERIFICATION_CODE_KEY);
        log.debug("saveCode = {} , code = {}",saveCode , code);
        return  code.equals(saveCode);
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

        String saveCode = (String)SessionUtils.get(UserSessionKeyUtil.IMG_VERIFICATION_CODE_KEY);
        log.debug("saveCode = {} , code = {}",saveCode , code);
        return  code.equals(saveCode);
    }

    private String decPassword(String encPassword,RSAPrivateKey privateKey ){

        String decPassqord = null;

        log.debug("开始进行解密");


        log.debug("encPassword = {}" ,encPassword);
        log.debug("privateKey={}" ,privateKey );
        byte[] en_result  = RSAUtil.hexStringToBytes(encPassword);//解决Bad arguments问题
        log.debug("en_result len  =  " + en_result.length);

        try{
            byte[] decPasswordByte = RSAUtil.decrypt(privateKey,en_result);

            log.debug("decPasswordByte.len = {}",decPasswordByte.length );
            String passStr = new String(decPasswordByte);
            log.debug("passStr = {}",passStr );
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
