package com.hqyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName PageController
 * @Date 2021/10/27 16:44
 * @Author XianJiu
 * @Description 负责转发页面
 */

@Controller
public class PageController {
    /**
     * 客户关怀
     * */
    @RequestMapping("/customer/customCare")
    public String customCare(){
        return "customer/customCare";
    }

    /**
     * 客户信息
     * */
    @RequestMapping("/customer/customerInfo")
    public String customerInfo(){
        return "customer/customerInfo";
    }

    /**
     * 客户反馈
     * */
    @RequestMapping("/customer/feedbackInfo")
    public String feedbackInfo(){
        return "customer/feedbackInfo";
    }

    /**
     * echarts
     * */
    @RequestMapping("/echarts/person")
    public String echarts(){
        return "echarts/person";
    }

    /**
     * 经理信息
     * */
    @RequestMapping("/manager/managerInfo")
    public String managerInfo(){
        return "manager/managerInfo";
    }

    /**
     * 服务记录
     * */
    @RequestMapping("/manager/servicelog")
    public String servicelog(){
        return "manager/servicelog";
    }

    /**
     * 用户管理
     * */
    @RequestMapping("/user/userInfo")
    public String userInfo(){
        return "user/userInfo";
    }



}
