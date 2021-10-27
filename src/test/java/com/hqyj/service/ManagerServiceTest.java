package com.hqyj.service;

import com.hqyj.MyApp;
import com.hqyj.dao.ManagerMapper;
import com.hqyj.pojo.Manager;
import com.hqyj.service.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
public class ManagerServiceTest {
    // 注入ManagerService
    @Autowired(required = false)
    private ManagerService managerService;

    // 注入ManagerMapper
    @Autowired(required = false)
    private ManagerMapper managerMapper;

    @Test
    public void select() {
        //HashMap<String, Object> select = managerService.select(new Manager());
        //List<Manager> list = (List<Manager>) select.get("list");
        //for (Manager manager : list) {
        //    System.err.println(manager);
        //}

    }

    @Test
    public void add() {
        Manager manager = new Manager();
        manager.setManagerName("杨经理");
        manager.setManagerSex("男");
        manager.setManagerBirthday("2021-10-21");
        manager.setManagerPhone("12345678911");
        manager.setManagerEmail("Email@qq.com");
        manager.setManagerEducation("本科");
        manager.setManagerIdnumber("12345678987654321");
        manager.setComments(null);
        HashMap<String, Object> add = managerService.add(manager);
        System.out.println(add.get("info"));

    }

    @Test
    public void update() {
        Manager manager = managerMapper.selectById(4);
        manager.setManagerSex("女");
        HashMap<String, Object> update = managerService.update(manager);
        System.out.println(update.get("info"));
    }

}