package com.hqyj.service;

import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
public interface IRoleService extends IService<Role> {
    //查询
    HashMap<String,Object> select(Role r);
    //新增
    HashMap<String, Object> add(Role r);
    //修改
    HashMap<String, Object> update(Role r);
    //删除
    HashMap<String, Object> del(Integer id);
    //根据id查询数据结果
    HashMap<String,Object> selectById(Integer id);

    //根据角色名查询
    Role selectByRoleParam(String roleName);

    //插入id到用户角色表
    Integer insertId(Integer adminId,Integer roleId);


}
