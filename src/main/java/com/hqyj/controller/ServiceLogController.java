package com.hqyj.controller;


import com.hqyj.pojo.Manager;
import com.hqyj.pojo.ServiceLog;
import com.hqyj.service.ServiceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * <p>
 * 服务记录表 前端控制器
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/service-log")
public class ServiceLogController {
    //注入ServiceLogService
    @Autowired
   ServiceLogService serviceLogService;

    @RequestMapping(value = "/addPage")
    public HashMap<String,Object> addPage(ServiceLog serviceLog, HttpSession session){
        return serviceLogService.addPage(serviceLog,session);
    }
    @RequestMapping(value = "/list")
    public HashMap<String, Object> list(ServiceLog serviceLog, HttpSession session){
        return serviceLogService.select(serviceLog, session);
    }

    @RequestMapping(value = "/add")
    public HashMap<String, Object> add(ServiceLog serviceLog){
        return serviceLogService.add(serviceLog);
    }

    @RequestMapping(value = "/update")
    public HashMap<String, Object> update(ServiceLog serviceLog){
        return serviceLogService.update(serviceLog);
    }

    @RequestMapping(value = "/del")
    public HashMap<String, Object> del(Integer serviceId){
        return serviceLogService.del(serviceId);
    }


}

