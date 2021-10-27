package com.hqyj.controller;


import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Manager;
import com.hqyj.service.ManagerService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * <p>
 * 经理表 前端控制器
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    // 注入ManagerService
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/list")
    public HashMap<String, Object> list(Manager manager, HttpSession session){
        Admin user =(Admin) session.getAttribute("user");

        System.err.println("user=========================================="+user.toString());
        return managerService.select(manager,session);
    }

    @RequestMapping(value = "/add")
    public HashMap<String, Object> add(Manager manager){
        return managerService.add(manager);
    }

    @RequestMapping(value = "/update")
    public HashMap<String, Object> update(Manager manager){
        return managerService.update(manager);
    }

    @RequestMapping(value = "/del")
    public HashMap<String, Object> del(Integer managerId){
        return managerService.del(managerId);
    }

}

