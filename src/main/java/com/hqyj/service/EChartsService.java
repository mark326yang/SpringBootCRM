package com.hqyj.service;

import java.util.HashMap;

public interface EChartsService {
    /**
     * 查询管理员，经理，客户的人员分布
     * */
    HashMap<String,Object> selectRoleType();
}
