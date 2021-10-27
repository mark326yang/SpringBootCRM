package com.hqyj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 启动浏览器访问
 * @Autor 伍军
 * @Date 2021/10/8 13:35
 * @Version 1.0
 **/
@Configuration
public class MyRunner implements CommandLineRunner {
    @Value("${openurl}")
    private boolean isOpen;
    @Value("${myurl}")
    private String myurl;
    @Value("${googleexcute}")
    private String googleexcute;
    @Value("${server.port}")
    private int port;

    @Override
    public void run(String... args) throws Exception {
        //拼接cmd命令
        // String cmd = googleexcute+" "+myurl+":"+port+"/admin/loginPage";
        String cmd = googleexcute+" "+myurl+":"+port;
        System.out.println("cmd="+cmd);
        if(isOpen){
            Runtime run = Runtime.getRuntime();
            run.exec(cmd);
        }

    }
}
