package com.hqyj.controller;

import com.hqyj.service.CustomerFeedbackService;
import com.hqyj.service.CustomerService;
import com.hqyj.service.ManagerService;
import com.hqyj.service.ServiceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ExcelController
 * @Date 2021/10/21 12:49
 * @Author XianJiu
 * @Description TODO
 */

@Controller
public class ExcelController {
    // 注入ManagerService接口
    @Autowired
    private ManagerService managerService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ServiceLogService serviceLogService;

    // 注入CustomerFeedbackService接口
    @Autowired
    private CustomerFeedbackService customerFeedbackService;

    @RequestMapping("/excelManager")
    public void excelManager(HttpServletResponse response){
        managerService.excelManager(response);
    }
    @RequestMapping("/excelCustomer")
    public void excelCustomer(HttpServletResponse response){
        customerService.excelCustomer(response);
    }

    @RequestMapping("/excelServiceLog")
    public void excelServiceLog(HttpServletResponse response){
        serviceLogService.excelServiceLog(response);
    }

    @RequestMapping(value = "/excelFeedback" )
    public void excelFeedback(HttpServletResponse response){
        customerFeedbackService.excelFeedback(response);
    }
}
