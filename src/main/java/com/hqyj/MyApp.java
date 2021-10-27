package com.hqyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/8 9:08
 * @Version 1.0
 **/
@ EnableScheduling
@SpringBootApplication
@MapperScan("com.hqyj.dao")
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class,args);
    }

}
