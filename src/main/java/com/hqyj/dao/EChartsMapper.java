package com.hqyj.dao;

import com.hqyj.pojo.RoleType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EChartsMapper {
    /**
     * 查询 管理员，经理，客户的人员分布
     * */
    List<RoleType> selectRoleType();
}
