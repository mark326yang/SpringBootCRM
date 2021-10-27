package com.hqyj.service;

import com.hqyj.MyApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
public class EChartsServiceTest {
    @Autowired(required = false)
    private EChartsService eChartsService;

    @Test
    public void selectRoleType() {
        HashMap<String, Object> map = eChartsService.selectRoleType();
        List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) map.get("list");
        System.err.println("集合大小"+list.size());
        for(int i =0 ;i<list.size();i++){
            System.err.println(list.get(i).get("name")+"=="+list.get(i).get("value"));
        }
    }
}