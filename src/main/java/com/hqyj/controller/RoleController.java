package com.hqyj.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Role;
import com.hqyj.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    //访问roleList页面
    @RequestMapping("/roleList")
    public String roleListPage(Model model, Role r){
        model.addAttribute("list",roleService.select(r));


        return "roleList";
    }

    //访问updatePage页面
    @RequestMapping("/updatePage")
    public String updatePage(Model model,Integer id){

        model.addAttribute("role",roleService.selectById(id));
        return "roleUpdate";
    }

    //修改
    @RequestMapping("/update")
    public String update(Model model, Role r){
        HashMap<String,Object> map = roleService.update(r);

        if(map.get("info").equals("保存成功")){
            model.addAttribute("info","保存成功");
        }else {
            model.addAttribute("info","保存失败");
        }

        //刷新
        return roleListPage(model,r);
    }

    //访问addPage页面
    @RequestMapping("/addPage")
    public String addPage(Model model,Integer id){
        return "roleAdd";
    }

    //新增
    @RequestMapping("/add")
    public String add(Model model, Role r){
        HashMap<String,Object> map = roleService.add(r);
        if(map.get("info").equals("保存成功")){
            model.addAttribute("info",map.get("info"));
            //刷新
            return roleListPage(model,r);
        }else {
            model.addAttribute("info",map.get("info"));
            return "roleAdd";
        }

    }
    //删除
    @RequestMapping("/del")
    public String del(Model model,Integer id){
        HashMap<String,Object> map = roleService.del(id);
        model.addAttribute("info",map.get("info"));
        //刷新页面
        Role r = new Role();
        return roleListPage(model,r);
    }







}

