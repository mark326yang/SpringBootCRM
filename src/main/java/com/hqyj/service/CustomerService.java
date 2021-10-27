package com.hqyj.service;

import com.hqyj.pojo.Customer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author zyh
 * @version 1.0
 * @items
 * @Date:on 2021/10/22 at 10:41
 */

public interface CustomerService {
    //查询所有客户信息

    //查询
    HashMap<String,Object> selectCustomer(Customer c, HttpSession session);

    /**
     * 新增用户信息
     * */
    HashMap<String, Object> add(Customer customer);

    /**
     * 修改用户信息
     * */
    HashMap<String, Object> update(Customer customer);

    /**
     * 删除用户信息
     * */
    HashMap<String, Object> del(Integer CustomerId);

    /**
     * 导出Excel表
     * */
    void excelCustomer(HttpServletResponse response);

   HashMap<String,Object> selectBirthday(Customer customer);


    public   boolean SendEmail(String email);


    public HashMap<String, Object> addPage(Customer customer, HttpSession session);



}
