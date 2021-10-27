package com.hqyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    //访问login页面
    @RequestMapping("/")
    public String exit( ){
        return "login";
    }
}
