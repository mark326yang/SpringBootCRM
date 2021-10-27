package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hqyj.dao.AdminMapper;
import com.hqyj.pojo.Admin;
import com.hqyj.service.AdminService;

import com.hqyj.utile.MdFive;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/9 8:54
 * @Version 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {

    //注入mapper
    @Autowired(required = false)
    AdminMapper adminMapper;

    //注入Md5工具类
    @Autowired
    MdFive mdFive;

    @Override
    public HashMap<String, Object> select(Admin a) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        //定义分页对象
        Page<Admin> page = new Page<>(a.getPage(),a.getRow());

        //条件构造器
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(a.getSysId()!=null,"sys_id",a.getSysId());
        queryWrapper.like(a.getSysName()!=null&&!a.getSysName().equals(""),"sys_name",a.getSysName());

            //查询数据库
        Page<Admin> aList =adminMapper.selectPage(page,queryWrapper);
            //当前页集合
        map.put("list",aList.getRecords());
            //总页数
        map.put("totalPage",aList.getPages());
            //总条数
        map.put("total",aList.getTotal());
            //当前页
        map.put("curPage",a.getPage());
        if(a.getPage()==1){
                //上一页
                map.put("prePage",1);

        }else{
                map.put("prePage",a.getPage()-1);

        }
        if(a.getPage()==aList.getPages()){
                //下一页
                map.put("nextPage",aList.getPages());

        }else{
                map.put("nextPage",a.getPage()+1);

        }
            //每页显示条数
          map.put("row",a.getRow());

        return map;
    }

    @Override
    public HashMap<String, Object> add(Admin a) {
        HashMap<String, Object> map = new HashMap<>();
        //查询用户名是否重名
        //创建条件构造器
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        //定义根据用户名查询的条件
        queryWrapper.eq("sys_name",a.getSysName());
        //查询用户名是否重名
        List<Admin> list = adminMapper.selectList(queryWrapper);

        if(list.size()>0){
            map.put("info","用户名重名");
        }else{//没重名
            String salt=UUID.randomUUID()+"";
            a.setSalt(salt);
            //加密密码
            String p= mdFive.encrypt(a.getSysPwd(),salt);
            a.setSysPwd(p);
            //新增
            int num = adminMapper.insert(a);
            if(num>0){
                map.put("info","保存成功");
            }else {
                map.put("info","保存错误");
            }

        }


        return map;


       /* HashMap<String, Object> map = new HashMap<String, Object>();
        //新增
        int num = adminMapper.insert(a);
        if(num>0){
                map.put("info","保存成功");
        }else{
                map.put("info","保存错误");
        }
        return map;*/
    }

    @Override
    public HashMap<String, Object> update(Admin a) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        String salt=UUID.randomUUID()+"";
        a.setSalt(salt);
        //加密密码
        String p= mdFive.encrypt(a.getSysPwd(),salt);
        a.setSysPwd(p);
        //修改
        int num = adminMapper.updateById(a);
        if(num>0){
                map.put("info","保存成功");
        }else{
                map.put("info","保存错误");
        }
        return map;
    }


    @Override
    public HashMap<String, Object> del(Integer a) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        //删除
        int num = adminMapper.deleteById(a);
        if(num>0){
            map.put("info","保存成功");
        }else{
            map.put("info","保存错误");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> login(Admin a, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        try{
            //1.用户令牌(用户名和密码对象)
            UsernamePasswordToken token = new UsernamePasswordToken(a.getSysName(),a.getSysPwd());
            //2.创建shiro的用户对象
            Subject su = SecurityUtils.getSubject();
            //3登录
            su.login(token);   //到MyRealm的认证方法
            map.put("info","登录成功");

            a= adminMapper.selectAdminBySysName(a.getSysName());

            session.setAttribute("user",a);//拿到用户全部信息
        }catch (UnknownAccountException u){
            u.printStackTrace();
            map.put("info","用户名不正确");
            System.out.println("用户名不正确");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            map.put("info","密码不正确");
            System.out.println("密码不正确");
        }
        return map;
    }


    @Override
    public HashMap<String, Object> selectById(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        Admin a = adminMapper.selectById(id);
        map.put("a",a);
        return map;
    }


}
