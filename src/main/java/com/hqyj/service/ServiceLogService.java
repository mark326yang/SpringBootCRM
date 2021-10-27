package com.hqyj.service;

import com.hqyj.pojo.Admin;
import com.hqyj.pojo.CustomerFeedback;
import com.hqyj.pojo.Manager;
import com.hqyj.pojo.ServiceLog;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * <p>
 * 服务记录表 服务类
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */
public interface ServiceLogService extends IService<ServiceLog> {
   //查询
    HashMap<String, Object> select(ServiceLog s, HttpSession session);
    //增加
    HashMap<String, Object> add(ServiceLog s);
    //删除
    HashMap<String, Object> del(Integer serviceId);
    //修改
    HashMap<String,Object> update(ServiceLog s);

 /**
  * 回显当前用户已完成的服务信息
  * */
 HashMap<String, Object> addPage(ServiceLog serviceLog, HttpSession session);

 //导出excl表
    void excelServiceLog(HttpServletResponse response);
}
