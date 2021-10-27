package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hqyj.pojo.Role;
import com.hqyj.dao.RoleMapper;
import com.hqyj.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    //注入mapper
    @Autowired(required = false)
    RoleMapper roleMapper;

    @Override
    public HashMap<String, Object> select(Role r) {
        HashMap<String,Object> map = new HashMap<>();
        //定义分页对象
        Page<Role> page = new Page<>(r.getPage(),r.getRow());

        /*-------------mybatit plus的分页条件查询---------*/
        //查询分页
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        //编号查询
        queryWrapper.eq(r.getRoleId()!=null,"role_id",r.getRoleId());
        //角色名模糊查询
        queryWrapper.like(!StringUtils.isEmpty(r.getRoleName())&&r.getRoleName()!=null,"role_name",r.getRoleName());

        Page<Role> rList=roleMapper.selectPage(page,queryWrapper);

        //当前页集合
        map.put("list",rList.getRecords());
        //总页数
        map.put("totalPage",rList.getPages());
        //总条数
        map.put("total",rList.getTotal());
        //当前页
        map.put("curPage",r.getPage());

        if(r.getPage()==1){
            //上一页
            map.put("prePage",1);
        }else {
            //上一页
            map.put("prePage",r.getPage()-1);
        }

        if(r.getPage()==rList.getPages()){
            //下一页
            map.put("nextPage",rList.getPages());
        }else {
            //下一页
            map.put("nextPage",r.getPage()+1);
        }


        //每页显示条数
        map.put("row",r.getRow());

        return map;



    }

    @Override
    public HashMap<String, Object> add(Role r) {
        HashMap<String, Object> map = new HashMap<>();
        //查询用户名是否重名
        //创建条件构造器
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        //定义根据用户名查询的条件
        queryWrapper.eq("role_name",r.getRoleName());
        //查询用户名是否重名
        List<Role> list = roleMapper.selectList(queryWrapper);

        if(list.size()>0){
            map.put("info","用户名重名");
        }else{//没重名

            //新增
            int num = roleMapper.insert(r);
            if(num>0){
                map.put("info","保存成功");
            }else {
                map.put("info","保存错误");
            }

        }


        return map;
    }

    @Override
    public HashMap<String, Object> update(Role r) {
        HashMap<String,Object> map = new HashMap<>();
        //修改
        int num = roleMapper.updateById(r);
        if(num>0){
            map.put("info","保存成功");
        }else {
            map.put("info","保存错误");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> del(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        //删除
        int num = roleMapper.deleteById(id);
        if(num>0){
            map.put("info","保存成功");
        }else {
            map.put("info","保存错误");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> selectById(Integer id) {
        HashMap<String,Object> map = new HashMap<>();
        Role r = roleMapper.selectById(id);
        map.put("r",r);
        return map;
    }

    @Override
    public Role selectByRoleParam(String roleName) {
        Role r = roleMapper.selectByParam(roleName);
        return r;
    }

    @Override
    public Integer insertId(Integer adminId, Integer roleId) {
        int num = roleMapper.interId(adminId,roleId);
        return num;
    }
}
