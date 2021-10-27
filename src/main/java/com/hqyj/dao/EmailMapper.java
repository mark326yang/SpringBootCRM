package com.hqyj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hqyj.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zyh
 * @version 1.0
 * @items
 * @Date:on 2021/10/25 at 14:08
 */

@Mapper
public interface EmailMapper extends BaseMapper<Customer> {
}
