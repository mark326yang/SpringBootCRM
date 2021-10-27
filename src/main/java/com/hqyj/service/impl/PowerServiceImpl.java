package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Power;
import com.hqyj.dao.PowerMapper;
import com.hqyj.pojo.Role;
import com.hqyj.service.IPowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@Service
public class PowerServiceImpl extends ServiceImpl<PowerMapper, Power> implements IPowerService {

    @Autowired(required = false)
    PowerMapper powerMapper;

    @Override
    public HashMap<String, Object> select(Power p) {
        HashMap<String,Object> map = new HashMap<>();
        //定义分页对象
        Page<Power> page = new Page<>(p.getPage(),p.getRow());
        /*-------------mybatit plus的分页条件查询---------*/
        //查询分页
        QueryWrapper<Power> queryWrapper = new QueryWrapper<>();
        //编号查询
        queryWrapper.eq(p.getPowerId()!=null,"power_id",p.getPowerId());
        //用户名模糊查询
        queryWrapper.like(!StringUtils.isEmpty(p.getPowerName())&&p.getPowerName()!=null,"power_name",p.getPowerName());

        Page<Power> pList=powerMapper.selectPage(page,queryWrapper);

        //当前页集合
        map.put("list",pList.getRecords());
        //总页数
        map.put("totalPage",pList.getPages());
        //总条数
        map.put("total",pList.getTotal());
        //当前页
        map.put("curPage",p.getPage());

        if(p.getPage()==1){
            //上一页
            map.put("prePage",1);
        }else {
            //上一页
            map.put("prePage",p.getPage()-1);
        }

        if(p.getPage()==pList.getPages()){
            //下一页
            map.put("nextPage",pList.getPages());
        }else {
            //下一页
            map.put("nextPage",p.getPage()+1);
        }


        //每页显示条数
        map.put("row",p.getRow());

        return map;
    }

    @Override
    public HashMap<String, Object> add(Power p) {
        HashMap<String, Object> map = new HashMap<>();
        //查询用户名是否重名
        //创建条件构造器
        QueryWrapper<Power> queryWrapper = new QueryWrapper<>();
        //定义根据用户名查询的条件
        queryWrapper.eq("power_name",p.getPowerName());
        //查询用户名是否重名
        List<Power> list = powerMapper.selectList(queryWrapper);

        if(list.size()>0){
            map.put("info","用户名重名");
        }else{//没重名

            //新增
            int num = powerMapper.insert(p);
            if(num>0){
                map.put("info","保存成功");
            }else {
                map.put("info","保存错误");
            }

        }


        return map;
    }

    @Override
    public HashMap<String, Object> update(Power p) {
        HashMap<String,Object> map = new HashMap<>();
        //修改
        int num = powerMapper.updateById(p);
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
        int num = powerMapper.deleteById(id);
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
        Power p = powerMapper.selectById(id);
        map.put("p",p);
        return map;
    }
}
