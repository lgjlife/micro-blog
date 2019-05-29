package com.microblog.user.web.controller;


import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.user.dao.model.User;
import com.microblog.user.service.service.UserFansService;
import com.microblog.user.service.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user/fans")
public class UserFansController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserFansService userFansService;


    @PrintUrlAnno
    @PostMapping("/concern")
    void concern(@RequestBody long concernId){

       Long currentUserId =  UserUtil.getUserId(request);
       if(currentUserId != null){

           userFansService.concerns(currentUserId,concernId);
       }

    }

    @PrintUrlAnno
    @DeleteMapping("/un-concern")
    void unConcern(long userId,long concernId){
        Long currentUserId =  UserUtil.getUserId(request);
        if(currentUserId != null){
            userFansService.unConcerns(currentUserId,concernId);
        }
    }


    @PrintUrlAnno
    @PutMapping("/concern")
    List<User> queryConcerns(long userId){
        Long currentUserId =  UserUtil.getUserId(request);
        if(currentUserId != null){

           List<User> users =  userFansService.queryConcerns(currentUserId);
           return users;
        }

        return  null;

    }


    @PrintUrlAnno
    @PutMapping("/concern")
    List<User> queryFans(long userId){
        Long currentUserId =  UserUtil.getUserId(request);
        if(currentUserId != null){
            List<User> users = userFansService.queryFans(currentUserId);
            return users;
        }
        return null;
    }
}
