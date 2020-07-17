package com.microblog.authorization.controller;

import com.microblog.authorization.keypair.KeyPairService;
import com.microblog.util.aop.syslog.anno.PrintUrlAnno;
import com.microblog.util.result.ResponseCode;
import com.microblog.util.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *功能描述 扽路相关
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private KeyPairService keyPairService;


    /**
     * 获取rsa的publickey,用于对密码加密
     * @param username
     * @return
     */
    @PrintUrlAnno
    @GetMapping("/publickey")
    public WebResult getPublicKey(@RequestParam("username") String username){

        String publicKey =  keyPairService.createPublickey(username);
        if(publicKey != null){
            return new WebResult(ResponseCode.SUCCESS.getCode(),publicKey,"请求成功");
        }
        return new WebResult(ResponseCode.FAIL.getCode(),publicKey,"请求失败");
    }
}
