package com.hqyj.controller;


import com.hqyj.pojo.Power;
import com.hqyj.pojo.Role;
import com.hqyj.service.IPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@Controller
@RequestMapping("/power")
public class PowerController {

    @Autowired
    IPowerService powerService;

    @RequestMapping("/powerList")
    public String powerListPage(Model model, Power p){
        model.addAttribute("list",powerService.select(p));

        return "powerList";
    }

    //访问updatePage页面
    @RequestMapping("/updatePage")
    public String updatePage(Model model,Integer id){

        model.addAttribute("power",powerService.selectById(id));
        return "powerUpdate";
    }

    //修改
    @RequestMapping("/update")
    public String update(Model model, Power p){
        HashMap<String,Object> map = powerService.update(p);

        if(map.get("info").equals("保存成功")){
            model.addAttribute("info","保存成功");
        }else {
            model.addAttribute("info","保存失败");
        }

        //刷新
        return powerListPage(model,p);
    }

    //访问addPage页面
    @RequestMapping("/addPage")
    public String addPage(Model model,Integer id){
        return "powerAdd";
    }

    //新增
    @RequestMapping("/add")
    public String add(Model model, Power p){
        HashMap<String,Object> map = powerService.add(p);
        if(map.get("info").equals("保存成功")){
            model.addAttribute("info",map.get("info"));
            //刷新
            return powerListPage(model,p);
        }else {
            model.addAttribute("info",map.get("info"));
            return "powerAdd";
        }

    }

    //删除
    @RequestMapping("/del")
    public String del(Model model,Integer id){
        HashMap<String,Object> map = powerService.del(id);
        model.addAttribute("info",map.get("info"));
        //刷新页面
        Power p = new Power();
        return powerListPage(model,p);
    }


}

