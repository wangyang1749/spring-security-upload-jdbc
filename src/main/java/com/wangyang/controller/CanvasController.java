package com.wangyang.controller;

import com.wangyang.service.ILoveWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CanvasController {

    @Autowired
    ILoveWallService loveWallService;

    @GetMapping("/canvas/{id}")
    public String canvas(Model model,@PathVariable int id){
        model.addAttribute("love",loveWallService.findById(id));
        return "hello";
    }
    @GetMapping("/edit/{id}")
    public String editCanvas(Model model,@PathVariable int id){
        model.addAttribute("love",loveWallService.findById(id));
        return "edit";
    }
    @GetMapping("/jsp")
    public String jsp(){
        return "index";
    }
}
