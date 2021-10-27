package com.hqyj.dao;

import com.hqyj.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role selectByParam(String roleParam);

    Integer interId(Integer sysId, Integer roleId);
}
