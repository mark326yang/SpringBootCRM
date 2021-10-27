package com.hqyj.realm;

import com.hqyj.dao.AdminMapper;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Role;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired(required = false)
    AdminMapper adminMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取认证通过的用户名
        Object userName =principals.fromRealm(this.getName()).iterator().next();

        /*------------角色分配--------------*/
        //根据用户名查询用户拥有的角色参数
        /*List<Admin> list = adminMapper.selectByUserName(userName+"");

        //创建授权对象
        SimpleAuthorizationInfo smp = new SimpleAuthorizationInfo();
        if(list.size()>0){
            //添加角色
            for(Role r:list.get(0).getRoleList()){
                //添加角色参数
                smp.addRole(r.getRoleParam());
                //

                System.err.println("给"+userName+"赋予了角色："+r.getRoleName());

            }
        }*/


        /*---------------权限分配---------------------*/

        List<String> list = adminMapper.selectPowerByName(userName+"");
        //创建授权对象
        SimpleAuthorizationInfo smp = new SimpleAuthorizationInfo();
        if(list.size()>0){
            //添加权限
            smp.addStringPermissions(list);
            for(String s:list){
                System.err.println("给"+userName+"赋予了权限："+s);
            }

        }

        return smp;

    }

    //认证（登录）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //取出用户名
        UsernamePasswordToken to =(UsernamePasswordToken)token;
        String userName = to.getPrincipal()+"";
        //验证用户名输入是否正确
        List<Admin> list=adminMapper.selectByName(userName);
        if(list.size()==0){
            throw new UnknownAccountException();
        }

        //创建凭证
        AuthenticationInfo au = new SimpleAuthenticationInfo(
                list.get(0).getSysName(),//用户名
                list.get(0).getSysPwd(),//密码
                new Md5Hash(list.get(0).getSalt()),//加密算法和盐值
                this.getName()//myrealm对象
        );
        return au;
    }
}
