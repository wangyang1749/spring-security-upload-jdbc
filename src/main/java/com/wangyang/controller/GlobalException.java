package com.wangyang.controller;

import com.wangyang.pojo.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalException {

    @RequestMapping("/error404")
    public R notFond404(){
        return  R.error("page not fond");
    }
}
