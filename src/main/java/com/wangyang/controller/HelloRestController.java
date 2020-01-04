package com.wangyang.controller;

import com.wangyang.exception.MyException;
import com.wangyang.pojo.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloRestController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Rest";
    }

    @RequestMapping("/exception")
    public String exception(){
        throw new MyException("123");
    }

    @RequestMapping("/user")
    public User user(){
        User user = new User();
        user.setUsername("wangyang");
        user.setPassword("12346");
        return user;
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin Rest";
    }

    /**
     * 会优先使用局部的异常处理
     */
//    @ExceptionHandler(Exception.class)
//    public String error404(Exception ex) {
//        return "404";
//    }
}
