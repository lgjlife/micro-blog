package com.microblog.user.web.controller;

import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.code.ReturnCode;
import com.microblog.common.dto.UserInfoDto;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.Result;
import com.microblog.common.result.WebResult;
import com.microblog.user.dao.model.User;
import com.microblog.user.service.ret.UserRet;
import com.microblog.user.service.service.UserInfoService;
import com.microblog.user.service.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *功能描述
 * @author lgj
 * @Description   用户信息
 * @date 2/20/19
*/

@RequestMapping("/user/info")
@RestController
@Slf4j
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;


    @Autowired
    private  HttpServletRequest request;



    @PrintUrlAnno
    @PostMapping("/header-img")
    @ResponseBody
    public BaseResult upLoadHeaderImg(HttpServletRequest request, HttpServletResponse response) {

        Long currentUserId =  UserUtil.getUserId(request);
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            try{
                String imgPath = userInfoService.upLoadHeaderImg(currentUserId,multiRequest);
                return new WebResult(Result.RESULT_SUCCESS,"获取用户头像图片成功",imgPath);
            }
            catch(Exception ex){
                ex.printStackTrace();
                return new WebResult(Result.RESULT_FAIL,"获取用户头像图片失败:"+ex.getMessage());
            }
        }
        return new WebResult(Result.RESULT_FAIL,"获取用户头像图片失败,图片不存在");
    }

    @PrintUrlAnno
    @GetMapping
    public BaseResult queryCurrentLoginInfo(){
        Long currentUserId =  UserUtil.getUserId(request);
        BaseResult result = null;
        UserInfoDto userInfoDto =  userInfoService.userInfo(currentUserId);
        if(userInfoDto  == null){

            result = new WebResult(UserRet.RESULT_FAIL,"查询用户信息失败，用户未登录");
            return   result ;

        }
        result = new WebResult(UserRet.RESULT_SUCCESS,"查询用户信息成功",userInfoDto);
        return   result ;
    }



    @PrintUrlAnno
    @GetMapping("/query")
    public User queryUserInfoById(@RequestParam("userId") Long userId){
        log.debug("userId = " + userId);
        return   userInfoService.userInfoById(userId) ;
    }

    @PrintUrlAnno
    @PostMapping("/setting")
    public BaseResult saveSetting(@RequestBody Map<String,Object> map){

        Long currentUserId =  UserUtil.getUserId(request);
        ReturnCode returnCode = userInfoService.saveSetting(currentUserId,map);
        BaseResult result = new WebResult(returnCode.getCode(),returnCode.getMessage());
        return   result ;
    }


    private  Long  getUserId(){
        Long userId =  (Long)request.getAttribute("userId");
        log.debug("userId = " + userId);
        return userId;
    }
}
