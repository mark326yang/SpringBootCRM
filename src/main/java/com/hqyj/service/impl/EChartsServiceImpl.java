package com.hqyj.service.impl;

import com.hqyj.dao.EChartsMapper;
import com.hqyj.pojo.RoleType;
import com.hqyj.service.EChartsService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName EChartsServiceImpl
 * @Date 2021/10/26 18:51
 * @Author XianJiu
 * @Description TODO
 */

@Service
public class EChartsServiceImpl implements EChartsService {
    /**
     * 注入EchartsMapper接口
     * */
    @Autowired(required = false)
    private EChartsMapper eChartsMapper;

    /**
     * @Date: 2021/10/27
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @Description: 封装查询出的数据为[{key1:value1,key2:value2},{...}]
     * */
    @Override
    public HashMap<String, Object> selectRoleType() {
        // 存储返回的map
        HashMap<String, Object> map = new HashMap<>();
        // 查询出数据
        List<RoleType> typeList = eChartsMapper.selectRoleType();
        // 用于存储为Echarts展示所需的数据格式
        List<HashMap<String, Object>> mapList = new ArrayList<>();
        for (int i =0;i<typeList.size();i++){
            HashMap<String, Object> mapL = new HashMap<>();
            // put name
            mapL.put("name",typeList.get(i).getName());
            // put value
            mapL.put("value",typeList.get(i).getNumber());
            // 存入List
            mapList.add(mapL);
        }
        if (mapList.size()>0) {
            map.put("list", mapList);
            map.put("info","成功");
        } else {
            map.put("info","失败");
        }

        return map;
    }
}
