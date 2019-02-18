package com.cloud.microblog.gateway.controller.user;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.common.result.BaseResult;
import com.cloud.microblog.common.result.WebResult;
import com.cloud.microblog.common.utils.UserRegexUtil;
import com.cloud.microblog.common.utils.encrypt.rsa.RSAUtil;
import com.cloud.microblog.gateway.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.Map;
import java.util.Random;

/**
 *功能描述 
 * @author lgj
 * @Description  user模块相关控制层
 * @date 2/18/19
*/
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {


    @Autowired
    UserService userService;

    /**
     *功能描述
     * @author lgj
     * @Description   提交登录请求
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @PostMapping("/login")
    public BaseResult login(@RequestBody Map<String, Object> requestMap){

        BaseResult result = null;

        String loginName = (String)requestMap.get("loginName");
        String loginNameType = (String)requestMap.get("loginNameType");
        String loginPassword = (String)requestMap.get("loginPassword");
        String imgVerificationCode = (String)requestMap.get("imgVerificationCode");


        if(StringUtils.isEmpty(loginName)
           || StringUtils.isEmpty(loginNameType)
           || StringUtils.isEmpty(loginPassword)
           || StringUtils.isEmpty(imgVerificationCode)){
            result = new WebResult(UserReturnCode.ERROR_PARAM);
            return  result;
        }


        userService.login(loginName,loginNameType,loginPassword);

        log.debug("loginName = " + loginName
                +"  loginNameType = " + loginNameType
                +"  loginPassword = " + loginPassword
                +"  imgVerificationCode = " + imgVerificationCode
        );

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        result = new WebResult(UserReturnCode.LOGIN_SUCCESS);

        return   result ;
    }

    /**
     *功能描述
     * @author lgj
     * @Description   提交注册请求
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @PostMapping("/register")
    public BaseResult register(@RequestBody Map<String, Object> requestMap){

        BaseResult result = null;

        String registerName = (String)requestMap.get("registerName");
        String registerNameType = (String)requestMap.get("registerNameType");
        String verificationCode = (String)requestMap.get("verificationCode");
        String registerPassword = (String)requestMap.get("registerPassword");
        String imgVerificationCode = (String)requestMap.get("imgVerificationCode");

        log.debug("registerName = " + registerName
                +"  registerNameType = " + registerNameType
                +"  verificationCode = " + verificationCode
                +"  registerPassword = " + registerPassword
                +"  imgVerificationCode = " + imgVerificationCode
        );

        //手机邮件校验码
        if(!userService.checkVerificationCode(verificationCode)){
            result = new WebResult(UserReturnCode.VALIDATE_CODE_CHECK_FAIL);
            return   result ;
        }
        //图片验证码
        if(!userService.checkImgVerificationCode(imgVerificationCode)){
            result = new WebResult(UserReturnCode.IMG_VALIDATE_CODE_CHECK_FAIL);
            return   result ;
        }
        //注册
        userService.register(registerName,registerNameType,registerPassword);





        result = new WebResult(UserReturnCode.LOGIN_SUCCESS);

        return   result ;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取手机或者邮箱的验证码
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @PostMapping("/verification/code")
    public BaseResult requestVerificationCode(@RequestBody Map<String, Object> requestMap){
        BaseResult result = null;
        String code = null;
        String registerName = (String)requestMap.get("registerName");
        String registerNameType = (String)requestMap.get("registerNameType");

        if(StringUtils.isEmpty(registerName)
        || StringUtils.isEmpty(registerNameType) ){
            result = new WebResult(UserReturnCode.ERROR_PARAM);
            return  result;
        }

        if(registerNameType == "phone"){
            if(!UserRegexUtil.isMobile(registerName)){
                result = new WebResult(UserReturnCode.ERROR_PARAM);
                return  result;
            }

            // TODO  电话获取验证码处理
            userService.sendPhoneVerificationCode(registerName);
        }
        else if(registerNameType == "email"){
            if(!UserRegexUtil.isEmail(registerName)){
                result = new WebResult(UserReturnCode.ERROR_PARAM);
                return  result;
            }
            // TODO 邮件获取验证码处理
            userService.sendEmailVerificationCode(registerName);
        }


        log.debug("registerName = " + registerName
                +"  registerNameType = " + registerNameType
        );

        result = new WebResult(UserReturnCode.LOGIN_SUCCESS,code);
        log.debug("result = " + result);
        return   result ;
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
    @PrintUrlAnno
    @GetMapping("/key")
    public BaseResult requestKey(){
        BaseResult result = null;
        Map map = userService.getRsaKey();
        result = new WebResult(UserReturnCode.REQUEST_RSA_KEY_SUCCESS,map);
        log.debug("result = " + result);
        return   result ;
    }


    /**
     *功能描述
     * @author lgj
     * @Description   退出登录处理
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @GetMapping("/logout")
    public String logout(){

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        log.debug("session id = " + session.getId());
        String value = (String) session.getAttribute("data");

        log.debug("value = " + value);

        return   String.valueOf(new Random().nextInt(100)) ;
    }



}



