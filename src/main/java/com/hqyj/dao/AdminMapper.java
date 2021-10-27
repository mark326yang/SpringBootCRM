package com.hqyj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hqyj.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/8 15:24
 * @Version 1.0
 **/

public interface AdminMapper extends BaseMapper<Admin> {


    //用户名查询
    @Select("select sys_id,sys_name,sys_pwd,salt,manager_id,customer_id from admin where sys_name=#{sysName}")
    List<Admin> selectByName(String sysName);

    @Select("select sys_id,sys_name,sys_pwd,manager_id,customer_id from admin where sys_name=#{sysName}")
    Admin selectAdminBySysName(String sysName);

    //根据用户名查询用户有的角色(sql映射配置（多对多）)
    List<Admin> selectByUserName(String sysName);

    //针对myreal的只需要权限参数
    List<String> selectPowerByName(String sysName);
}
