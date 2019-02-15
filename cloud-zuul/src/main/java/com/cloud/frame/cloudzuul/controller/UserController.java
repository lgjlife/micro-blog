package com.cloud.frame.cloudzuul.controller;

import com.cloud.frame.cloudzuul.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-18 21:24
 **/

@Api("/server")
@Controller
@RequestMapping("/server")
public class UserController {

    @ApiOperation(value="/{id}",notes = "根据ID获取用户",httpMethod="GET")
    @RequestMapping("/{id}")
    public String  getUser(@PathVariable Integer id, Model model) {

        model.addAttribute("server",new User(id,"张三",20,"中国广州"));
        return "/server/detail";
    }

    @ApiOperation(value="/{list}",notes = "获取用户列表",httpMethod="GET")
    @RequestMapping("/list")
    public String  listUser(Model model) {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i <10; i++) {
            userList.add(new User(i,"张三"+i,20+i,"中国广州"));
        }

        model.addAttribute("users", userList);
        return "/server/list";
    }
}
