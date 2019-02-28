package com.cloud.microblog.chat.controller;


import com.cloud.microblog.chat.dto.ChatUserDto;
import com.cloud.microblog.chat.ret.ChatReturn;
import com.cloud.microblog.chat.service.UserService;
import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.common.result.BaseResult;
import com.cloud.microblog.common.result.WebResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService  userService;

    @ApiOperation(value = "/user/list")
    @PrintUrlAnno
    @RequestMapping("/list")
    public BaseResult userList(){

        BaseResult result = null;

        List<ChatUserDto> chatUserDtos = userService.list();


        result = new WebResult(ChatReturn.CHAT_USER_LIST_SUCCESS,chatUserDtos);

        return  result;

    }

}
