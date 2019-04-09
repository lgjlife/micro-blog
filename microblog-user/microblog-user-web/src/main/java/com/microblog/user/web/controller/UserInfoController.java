package com.microblog.user.web.controller;

import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.code.ReturnCode;
import com.microblog.common.code.UserReturnCode;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.user.dao.model.User;
import com.microblog.user.service.service.UserInfoService;
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
    @PostMapping("/img")
    @ResponseBody
    public BaseResult upLoadHeaderImg(HttpServletRequest request, HttpServletResponse response) {


        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            try{
                String imgPath = userInfoService.upLoadHeaderImg(multiRequest);
                return new WebResult(UserReturnCode.HEADER_FILE_SUCCESS,imgPath);
            }
            catch(Exception ex){
                ex.printStackTrace();
                return new WebResult(UserReturnCode.HEADER_FILE_NULL);
            }
        }
        return new WebResult(UserReturnCode.HEADER_FILE_NULL);
    }

    @PrintUrlAnno
    @GetMapping
    public BaseResult queryCurrentLoginInfo(){
        BaseResult result = null;
        User user =  userInfoService.userInfo();
        if(user  == null){

            result = new WebResult(UserReturnCode.QUERY_USER_INFO_FAIL);
            return   result ;

        }
        result = new WebResult(UserReturnCode.QUERY_USER_INFO_SUCCESS,user);
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

        ReturnCode returnCode = userInfoService.saveSetting(map);
        BaseResult result = new WebResult(returnCode);
        return   result ;
    }


    private  Long  getUserId(){
        Long userId =  (Long)request.getAttribute("userId");
        log.debug("userId = " + userId);
        return userId;
    }
}
