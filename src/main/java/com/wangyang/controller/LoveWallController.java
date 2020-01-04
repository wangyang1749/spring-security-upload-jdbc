package com.wangyang.controller;

import com.wangyang.pojo.LoveWall;
import com.wangyang.pojo.R;
import com.wangyang.service.ILoveWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/lovewall")
public class LoveWallController {

    @Autowired
    ILoveWallService loveWallService;

    @PostMapping("/add")
    public R add(LoveWall loveWall){
        loveWall.setDate(new Date());
        loveWallService.add(loveWall);
        return  R.ok("add success");
    }

    @RequestMapping("/page")
    public Page page(int page,int size){
        return  loveWallService.findPage(0,2);
    }

    @GetMapping("/{id}")
    public LoveWall findById(@PathVariable int id){
        return loveWallService.findById(id);
    }


}
