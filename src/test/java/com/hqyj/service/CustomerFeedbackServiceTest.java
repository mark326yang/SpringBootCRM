package com.hqyj.service;

import com.hqyj.MyApp;
import com.hqyj.dao.CustomerFeedbackMapper;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.CustomerFeedback;
import com.hqyj.pojo.ServiceLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
public class CustomerFeedbackServiceTest {
    // 注入CustomerFeedbankMapper
    @Autowired(required = false)
    private CustomerFeedbackMapper customerFeedbackMapper;

    @Test
    public void select() {
        Admin admin = new Admin();
        admin.setCustomerId(4);
        CustomerFeedback customerFeedback = customerFeedbackMapper.selectCustomerAndManagerByAdminSysName(admin);
        System.err.println(customerFeedback.toString());
        List<ServiceLog> serviceLog = customerFeedbackMapper.selectServiceLogByCustomerFeedback(customerFeedback);
        System.err.println(serviceLog.toString());
    }

    @Test
    public void add() {
        CustomerFeedback customerFeedback = new CustomerFeedback();
        customerFeedback.setCustomerName("张麻子");
        customerFeedback.setManagerName(" 张牧之");
        customerFeedback.setCustomerId(4);
        customerFeedback.setManagerId(19);
        customerFeedback.setCustomerStatisfaction("比较满意");
        customerFeedback.setCustomerFeedback("这是一次不错的服务");
        int num = customerFeedbackMapper.insert(customerFeedback);
        if (num > 0){
            System.out.println("保存成功");
        }else {
            System.out.println("保存失败");
        }
    }
}