package com.discussion.qa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 默认主页
 */
@Controller
public class IndexController {

//    @GetMapping("/hello")
//    public String hello(@RequestParam(name = "name")String name, Model model){
//        model.addAttribute("name", name);
//        return "index";
//    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
