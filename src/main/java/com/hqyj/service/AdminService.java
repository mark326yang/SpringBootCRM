package com.hqyj.service;

import com.hqyj.pojo.Admin;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/9 8:51
 * @Version 1.0
 **/
public interface AdminService {
    //查询
    HashMap<String,Object> select(Admin a);
    //新增
    HashMap<String,Object> add(Admin a);
    //修改
    HashMap<String,Object> update(Admin a);
    //删除
    HashMap<String,Object> del(Integer id);

    //登录
    HashMap<String,Object> login(Admin a, HttpSession session);

    //根据id查询
    HashMap<String,Object> selectById(Integer id);

}
