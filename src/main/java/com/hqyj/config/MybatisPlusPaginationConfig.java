package com.hqyj.config;

/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/9 14:38
 * @Version 1.0
 **/

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 分页插件配置
 * @Autor 伍军
 * @Date 2021/8/6 13:50
 * @Version 1.0
 **/
@Configuration
public class MybatisPlusPaginationConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
