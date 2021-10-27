package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.hqyj.MyApp;
import lombok.Data;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;

/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/8 15:16
 * @Version 1.0
 **/
@Data
@TableName("admin")
public class Admin extends MyPage implements Serializable {

    //主键
    @TableId(value = "sys_id",type = IdType.AUTO)
    private Integer sysId;

    //TableField 非主键的注解 ，定义的属性和列名的映射关系
    @TableField(value = "sys_name")
    private String sysName;

    @TableField(value = "sys_pwd")
    private String sysPwd;

    @TableField(value = "salt")
    private String salt;

    @TableField(value = "manager_id")
    private Integer managerId;

    @TableField(value = "customer_id")
    private Integer customerId;

    @TableField(exist = false)
    private List<Role> roleList;


}
