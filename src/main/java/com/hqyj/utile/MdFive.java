package com.hqyj.utile;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

/**
 * @Author 伍军
 */
@Component
public class MdFive {
    /**
     *
     * @param password 要加密的密码
     * @param saltValue 盐值
     * @return
     */
    public String encrypt(String password,String saltValue){
        //创建一个MD5的盐值对象
        Object salt = new Md5Hash(saltValue);
        //生成加密的字符串
        Object result = new SimpleHash("MD5", password, salt, 1024);

        return result+"";
    }


    public static void main(String[] args) {
        MdFive d = new MdFive();
        String info = d.encrypt("123","rock");
        System.out.println(info);
    }

}