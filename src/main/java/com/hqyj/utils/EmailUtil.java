package com.hqyj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/15 13:53
 * @Version 1.0
 **/
@Component
public class EmailUtil {

    //注入java邮件发送服务列
    @Autowired
    JavaMailSender javaMailSender;

    //发送者邮箱
    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * @param toEmail 接受者邮箱
     * @param title   邮件标题
     * @param content 邮件正文
     * @return
     */
    public String sendEmail(String toEmail, String title, String content) {

        try {
            //创建一个普通邮件对象
            SimpleMailMessage message = new SimpleMailMessage();
            //发送者邮箱
            message.setFrom(fromEmail);
            //接收者邮箱
            message.setTo(toEmail);
            //邮件标题
            message.setSubject(title);
            //邮件正文
            message.setText(content);
            //发送邮箱
            javaMailSender.send(message);
            System.out.println("邮箱发送成功");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("邮箱发送失败");
        }

        return "fail";
    }

}
