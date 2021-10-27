package com.hqyj.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @ClassName ErrorPageConfig
 * @Date 2021/10/27 16:18
 * @Author XianJiu
 * @Description 配置范围错误的页面
 */

@Configuration
public class ErrorPageConfig {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/Error/404.do");
                ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN, "/Error/403.do");
                ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/Error/500.do");
                factory.addErrorPages(errorPage404,errorPage403,errorPage500);
            }
        };
    }

}
