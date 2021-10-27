package com.hqyj.service.impl;

import com.hqyj.MyApp;
import com.hqyj.dao.CustomerMapper;
import com.hqyj.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author zyh
 * @version 1.0
 * @items
 * @Date:on 2021/10/25 at 16:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
public class CustomerServiceImplTest {

    @Autowired(required = false)
    CustomerService service;

    @Test
    public  void contextLoads() {
    }
    @Test
    public void sendEmail() {
        service.SendEmail("1451265106@qq.com");
    }
}