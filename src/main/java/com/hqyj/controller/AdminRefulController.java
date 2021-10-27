package com.hqyj.controller;


import com.hqyj.pojo.Admin;
import com.hqyj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/adminReful")
public class AdminRefulController {

    @Autowired
    AdminService adminService;


    @RequestMapping("/login")
    public HashMap<String,Object> login(Admin a, HttpSession session){

        return adminService.login(a,session);
    }


}
