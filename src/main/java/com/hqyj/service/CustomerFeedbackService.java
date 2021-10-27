package com.hqyj.service;

import com.hqyj.pojo.CustomerFeedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hqyj.pojo.Manager;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * <p>
 * 客户反馈表 服务类
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
public interface CustomerFeedbackService {
    /**
     * 查询出 当前登录的用户的 客户名称和为他服务的经理的经理编号
     * */
    HashMap<String, Object> select(CustomerFeedback customerFeedback, HttpSession session);

    /**
     * 增加客户反馈记录
     * */
    HashMap<String, Object> add(CustomerFeedback customerFeedback);

    /**
     * 回显当前用户已完成的服务信息
     * */
    HashMap<String, Object> addPage(CustomerFeedback customerFeedback, HttpSession session);

    /**
     * Excel表导出
     * */
    void excelFeedback(HttpServletResponse response);
}
