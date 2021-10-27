package com.hqyj.service;

import com.hqyj.pojo.Power;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hqyj.pojo.Role;

import java.util.HashMap;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
public interface IPowerService extends IService<Power> {

    //查询
    HashMap<String,Object> select(Power p);
    //新增
    HashMap<String, Object> add(Power p);
    //修改
    HashMap<String, Object> update(Power p);
    //删除
    HashMap<String, Object> del(Integer id);
    //根据id查询数据结果
    HashMap<String,Object> selectById(Integer id);

}
