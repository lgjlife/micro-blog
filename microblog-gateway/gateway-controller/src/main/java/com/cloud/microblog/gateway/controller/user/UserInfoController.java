package com.cloud.microblog.gateway.controller.user;

import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.common.result.BaseResult;
import com.cloud.microblog.common.result.WebResult;
import com.cloud.microblog.gateway.dao.model.User;
import com.cloud.microblog.gateway.service.user.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    @PrintUrlAnno
    @PostMapping("/img")
    @ResponseBody
    public BaseResult upLoadHeaderImg(HttpServletRequest request, HttpServletResponse response) {

        List<MultipartFile> fileList = new ArrayList<>();
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            fileList = multiRequest.getFiles("file");
            if (fileList == null || fileList.size() <= 0) {

                log.debug("file is null");
                return new WebResult(UserReturnCode.HEADER_FILE_NULL);
            }

            for(MultipartFile file:fileList){
                log.debug("{}--{}",file.getName(),file.getOriginalFilename());

                String path = "/nginx/microblog/static/img/header";
                File saveFile = new File(path);
                if(!saveFile.exists()) {
                    log.debug("path:{} not exists,create dir");
                    saveFile.mkdir();
                    log.debug("saveFile.exists? {}",saveFile.exists());
                }
                Integer ran = new Random().nextInt(10000);
                File newfile = new File(path+"/"+ran+file.getOriginalFilename());
                log.debug("newfile={}",newfile.getAbsolutePath());
                try{
                    file.transferTo(newfile);
                    log.debug("文件上传成功！");
                    return new WebResult(UserReturnCode.HEADER_FILE_SUCCESS);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
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
}
