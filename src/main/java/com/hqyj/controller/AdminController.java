package com.hqyj.controller;

import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Role;
import com.hqyj.service.AdminService;
import com.hqyj.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/9 9:03
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    IRoleService roleService;

    @RequestMapping("/test")
    public String test(HttpSession session){
        System.out.println("user=="+session.getAttribute("user"));
        return "login";
    }

/*    //访问login页面
    @RequestMapping("/exit")
    public String exit( ){

        return "login";
    }*/


    //访问adminList页面
    @RequestMapping("/adminListPage")
    public String adminListPage(Model model,Admin a){
        model.addAttribute("list",adminService.select(a));

        return "redirect:/web/adminList.html";
    }

    //加载表格数据
    @RequestMapping("/adminList")
    @ResponseBody
    public  HashMap<String,Object> listPage(Admin a){
        return adminService.select(a);
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<String,Object>  update(Admin a){
        return adminService.update(a);
    }
    //新增
    @RequestMapping("/add")
    @ResponseBody
    public HashMap<String,Object> add( Admin a){
        return adminService.add(a);
    }

  /*  //删除
    @RequestMapping("/del")
    @ResponseBody
    public HashMap<String,Object> del(Integer id){
        return adminService.del(id);
    }*/

    //删除
    @RequestMapping("/del")
    public String del(Model model,Integer id){
        HashMap<String,Object> map = adminService.del(id);
//        model.addAttribute("info",map.get("info"));
        //刷新页面
        return "redirect:/web/userInfo.html";
    }

    //访问分配角色页面
    @RequestMapping("/assignRolePage")
    public String assignRolePage(Model model,Integer id){
        model.addAttribute("admin",adminService.selectById(id));
        //刷新页面
        return "assignRole";
    }

    //分配角色
    @RequestMapping("/assignRole")
    public String assignRole(Model model, Integer sysId,String roleParam) {
        System.err.println("=====sysId==============" + sysId);
        System.err.println("=====roleParam==============" + roleParam);
        //通过角色名查询到角色
        Role r = roleService.selectByRoleParam(roleParam);
//        r.getRoleId(),sysId直接插入中间表
        int num = roleService.insertId(sysId,r.getRoleId());
        System.out.println("num========" + num);

//        HashMap<String,Object> map = roleService.assignRole(r);
//        model.addAttribute("info",map.get("info"));
        //刷新页面
        return "redirect:/web/userInfo.html";
    }

}
