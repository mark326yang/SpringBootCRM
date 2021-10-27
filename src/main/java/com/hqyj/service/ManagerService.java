package com.hqyj.service;

import com.hqyj.pojo.Manager;
import com.baomidou.mybatisplus.extension.service.IService;
import org.omg.CORBA.ObjectHelper;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * <p>
 * 经理表 服务类
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */
public interface ManagerService  {
    /**
     * 查询经理信息
     * */
    HashMap<String, Object> select(Manager manager, HttpSession session);

    /**
     * 新增经理信息
     * */
    HashMap<String, Object> add(Manager manager);

    /**
     * 修改经理信息
     * */
    HashMap<String, Object> update(Manager manager);

    /**
     * 删除经理信息
     * */
    HashMap<String, Object> del(Integer managerId);

    /**
     * 导出Excel表
     * */
    void excelManager(HttpServletResponse response);
}
