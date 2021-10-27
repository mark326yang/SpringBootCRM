package com.hqyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Date 2021/10/21 8:39
 * @Author XianJiu
 * @Description TODO
 */

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /*@RequestMapping("/")
    public String login(){
        return "redirect:/web/login.html";
    }*/



}
