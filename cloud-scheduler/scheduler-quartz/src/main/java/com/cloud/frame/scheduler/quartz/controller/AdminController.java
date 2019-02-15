package com.cloud.frame.scheduler.quartz.controller;

import com.cloud.frame.scheduler.quartz.dao.model.Admin;
import com.cloud.frame.scheduler.quartz.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 00:18
 **/

@Controller
@RequestMapping("/admin")
public class AdminController {

    private  static  final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    AdminService adminService;

    @GetMapping("/list1")
    public List<Admin> queryAdmin(){

        log.info("访问 admin/list");
        return adminService.queryAdmin();
    }



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

@Getter
@Setter
@AllArgsConstructor
 class User {
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    //省略get和set方法、构造函数

}
