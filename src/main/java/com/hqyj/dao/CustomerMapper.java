package com.hqyj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hqyj.pojo.Customer;
import com.hqyj.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @items
 * @Date:on 2021/10/22 at 10:51
 */
@Mapper
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {

    List<Customer> selectCustomerNameByManager(Manager admin);


}
