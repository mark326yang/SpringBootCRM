package com.hqyj.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hqyj.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig {
    /**
     * 配置密码匹配器
     * @return
     */
    @Bean(name={"hashedCred"})
    public HashedCredentialsMatcher getMatcher(){
        HashedCredentialsMatcher hsc = new HashedCredentialsMatcher();
        //加密算法
        hsc.setHashAlgorithmName("MD5");
        //加密次数
        hsc.setHashIterations(1024);
        return hsc;
    }
    /**
     * 配置自定义的 realm
     * @param hs
     * @return
     */
    @Bean(name={"myRealm"})
    public MyRealm getRealm(@Qualifier("hashedCred") HashedCredentialsMatcher hs ){
        MyRealm realm = new MyRealm();
        realm.setCredentialsMatcher(hs);
        return realm;
    }
    /**
     * 配置安全管理类
     * @return
     */
    @Bean(name={"securityManager"})
    public DefaultWebSecurityManager getSecurity(@Qualifier("myRealm") MyRealm realm){
        DefaultWebSecurityManager dm = new DefaultWebSecurityManager();
        dm.setRealm(realm);
        return dm;
    }

    @Bean
    public LifecycleBeanPostProcessor getProcess(){
        return new LifecycleBeanPostProcessor();
    }

    //html 标签配置
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    /**
     * 配置权限
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager sm){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(sm);
        //配置登录页面
        bean.setLoginUrl("/");
        //配置无此权限访问页面
        bean.setUnauthorizedUrl("/web/noPower.html");
        //创建map对象
        HashMap<String,String> map = new HashMap<>();
        //配置访问adminList页面必须登录才能访问

        //配置退出根地址页面
        map.put("/admin/exit","logout");
        map.put("/web/*","authc");
/*        //添加角色配置
        map.put("/web/userInfo1.html","roles[admin]");
        map.put("/web/managerInfo.html","roles[manager]");*/


        //添加权限配置
//        map.put("/admin/adminList","perms[admin:list]");
//        map.put("/admin/addPage","perms[admin:add]");
//        map.put("/admin/empList","perms[emp:list]");
//        map.put("/admin/empAdd","perms[emp:list]");

        //注入bean中
        bean.setFilterChainDefinitionMap(map);

        return bean;
    }
}