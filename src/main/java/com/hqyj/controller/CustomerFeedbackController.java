package com.hqyj.controller;


import com.hqyj.pojo.CustomerFeedback;
import com.hqyj.service.CustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * <p>
 * 客户反馈表 前端控制器
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@RestController
@RequestMapping("/feedback")
public class CustomerFeedbackController {
    // 注入Cust
    @Autowired
    private CustomerFeedbackService customerFeedbackService;
    @RequestMapping(value = "/list")
    public HashMap<String, Object> list(CustomerFeedback customerFeedback, HttpSession session){
        return customerFeedbackService.select(customerFeedback,session);
    }

    @RequestMapping(value = "/add")
    public HashMap<String, Object> add(CustomerFeedback customerFeedback){
        return customerFeedbackService.add(customerFeedback);
    }

    @RequestMapping(value = "/addPage")
    public HashMap<String, Object> addPage(CustomerFeedback customerFeedback, HttpSession session){
        return customerFeedbackService.addPage(customerFeedback,session);
    }


}

