package com.microblog.user.web.controller;


import com.microblog.user.dao.model.UserDelete;
import com.microblog.user.service.service.UserDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user-delete")
public class UserDeleteController {


    @Autowired
    UserDeleteService userDeleteService;


    @GetMapping
    private List<UserDelete> query(){

        return  userDeleteService.query();
    }

    @DeleteMapping
    public void delete(List<Long>  uids){
        userDeleteService.delete(uids);
    }


}
