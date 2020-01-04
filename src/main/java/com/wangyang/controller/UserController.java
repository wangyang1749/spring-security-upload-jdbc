package com.wangyang.controller;

import com.wangyang.pojo.User;
import com.wangyang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
//    @Autowired
    IUserService userService;

    @RequestMapping("/addUser")
    public String addUser(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("17497485");
        userService.add(user);
        return "Data inserted successfully";
    }
}
