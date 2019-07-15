package com.microblog.user.web.controller;


import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.code.UserReturnCode;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.Result;
import com.microblog.common.result.WebResult;
import com.microblog.common.utils.UserRegexUtil;
import com.microblog.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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



  //  @Autowired
  //  RedisStringUtil redisStringUtil;

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
            result = new WebResult(UserReturnCode.ERROR_PARAM.getCode(),UserReturnCode.ERROR_PARAM.getMessage());
            return  result;
        }

        log.debug("loginName = " + loginName
                +"  loginNameType = " + loginNameType
                +"  loginPassword = " + loginPassword
                +"  imgVerificationCode = " + imgVerificationCode
        );
        //登录请求
        return userService.login(loginName,loginPassword);
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

       /* //手机邮件校验码
        if(!userService.checkVerificationCode(verificationCode)){
            result = new WebResult(UserReturnCode.VALIDATE_CODE_CHECK_FAIL);
            log.debug(UserReturnCode.VALIDATE_CODE_CHECK_FAIL.getMessage());
            return   result ;
        }
        //图片验证码
        if(!userService.checkImgVerificationCode(imgVerificationCode)){
            result = new WebResult(UserReturnCode.IMG_VALIDATE_CODE_CHECK_FAIL);
            log.debug(UserReturnCode.IMG_VALIDATE_CODE_CHECK_FAIL.getMessage());
            return   result ;
        }*/
        //注册请求
        UserReturnCode returnCode = userService.register(registerName,registerPassword);
        result = new WebResult(returnCode.getCode(),returnCode.getMessage());
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
            result = new WebResult(UserReturnCode.ERROR_PARAM.getCode(),UserReturnCode.ERROR_PARAM.getMessage());
            return  result;
        }
        log.debug("registerName = " + registerName
                +"  registerNameType = " + registerNameType
        );

        //是电话号码
        if(UserRegexUtil.isMobile(registerName)){
            if(userService.sendPhoneVerificationCode(registerName)){
                result = new WebResult(UserReturnCode.VALIDATE_CODE_SEND_SUCCESS.getCode(),UserReturnCode.VALIDATE_CODE_SEND_SUCCESS.getMessage());
                return result;
            }
        }
        //是邮件地址类型
        else if(UserRegexUtil.isEmail(registerName)){
            if(userService.sendEmailVerificationCode(registerName)){
                result = new WebResult(UserReturnCode.VALIDATE_CODE_SEND_SUCCESS.getCode(),UserReturnCode.VALIDATE_CODE_SEND_SUCCESS.getMessage());
                return result;
            }
        }

        result = new WebResult(UserReturnCode.VALIDATE_CODE_SEND_FAIL.getCode(),UserReturnCode.VALIDATE_CODE_SEND_FAIL.getMessage());
        return result;
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
    public BaseResult requestKey(@RequestParam("name")  String name){
        BaseResult result = null;
       // String name  = (String) requestMap.get("name");
        log.debug("name = {}",name);
        Map map = userService.getRsaKey(name);
        result = new WebResult(Result.RESULT_SUCCESS,"获取RSA 的 modulus/exponent 成功",map);
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
    @PostMapping("/logout")
    public BaseResult logout(){
        BaseResult result = null;
        UserReturnCode code = userService.logout();
        result = new WebResult(code.getCode(),code.getMessage());
        return   result ;
    }

}

