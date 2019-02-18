package com.cloud.microblog.gateway.controller.user;


import com.cloud.microblog.common.utils.UserRegexUtil;
import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.common.result.BaseResult;
import com.cloud.microblog.common.result.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

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

        String loginName = (String)requestMap.get("loginName");
        String loginNameType = (String)requestMap.get("loginNameType");
        String loginPassword = (String)requestMap.get("loginPassword");
        String imgVerificationCode = (String)requestMap.get("imgVerificationCode");

        log.debug("loginName = " + loginName
                +"  loginNameType = " + loginNameType
                +"  loginPassword = " + loginPassword
                +"  imgVerificationCode = " + imgVerificationCode
        );
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        BaseResult result = new WebResult(UserReturnCode.LOGIN_SUCCESS);

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

        BaseResult result = new WebResult(UserReturnCode.LOGIN_SUCCESS);

        log.debug("result = " + result);

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
        }
        else if(registerNameType == "email"){
            if(!UserRegexUtil.isEmail(registerName)){
                result = new WebResult(UserReturnCode.ERROR_PARAM);
                return  result;
            }
            // TODO 邮件获取验证码处理
        }


        log.debug("registerName = " + registerName
                +"  registerNameType = " + registerNameType
        );

        result = new WebResult(UserReturnCode.LOGIN_SUCCESS);
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
