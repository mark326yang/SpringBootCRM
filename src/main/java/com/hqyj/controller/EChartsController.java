package com.hqyj.controller;

import com.hqyj.service.EChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @ClassName EchartsController
 * @Date 2021/10/26 18:45
 * @Author XianJiu
 * @Description TODO
 */

@RestController
@RequestMapping("/echarts")
public class EChartsController {
    @Autowired(required = false)
    private EChartsService eChartsService;

    @RequestMapping("/list")
    public HashMap<String,Object> echarts(){
        return eChartsService.selectRoleType();
    }
}
