package com.hqyj.dao;

import com.hqyj.pojo.Admin;
import com.hqyj.pojo.ServiceLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 服务记录表 Mapper 接口
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */
public interface ServiceLogMapper extends BaseMapper<ServiceLog> {
/**
 * 查询当前登录的用户的 经理名称和经理编号
 * */
   /**
    * 用当前用户查询出 经理是否已为本人服务完成
    * */
   List<ServiceLog> selectCustomerNameServiceLogByManager(Admin admin);
}
