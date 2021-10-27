package com.hqyj.dao;

import com.hqyj.pojo.Admin;
import com.hqyj.pojo.CustomerFeedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hqyj.pojo.ServiceLog;

import java.util.List;

/**
 * <p>
 * 客户反馈表 Mapper 接口
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
public interface CustomerFeedbackMapper extends BaseMapper<CustomerFeedback> {
    /**
     * 查询当前登录的用户的 客户名称和为他服务的经理的经理编号
     * */
    CustomerFeedback selectCustomerAndManagerByAdminSysName(Admin admin);

    /**
     * 用当前用户查询出 经理是否已为本人服务完成
     * */
    List<ServiceLog> selectServiceLogByCustomerFeedback(CustomerFeedback feedback);
}
