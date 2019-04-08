package com.microblog.chat.controller;


import com.microblog.chat.dto.ChatUserDto;
import com.microblog.chat.ret.ChatReturn;
import com.microblog.chat.service.UserService;
import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
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
