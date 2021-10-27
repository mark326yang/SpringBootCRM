package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 经理表
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Manager extends MyPage implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 经理编号
     */
    @TableId(value = "manager_id", type = IdType.AUTO)
    private Integer managerId;

    /**
     * 经理姓名
     */
    private String managerName;

    /**
     * 性别
     */
    private String managerSex;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getManagerBirthday() {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //try {
        //    managerBirthday = sdf.parse(managerBirthday);
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //}
        return managerBirthday;
    }

    public void setManagerBirthday(String managerBirthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(managerBirthday != null){
            try {
                this.managerBirthday = sdf.parse(managerBirthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            this.managerBirthday = new Date();
        }
    }

    /**
     * 出生日期
     */
    private Date managerBirthday;

    /**
     * 联系电话
     */
    private String managerPhone;

    /**
     * 邮箱地址
     */
    private String managerEmail;

    /**
     * 学历
     */
    private String managerEducation;

    /**
     * 身份证号码
     */
    private String managerIdnumber;

    /**
     * 备注
     */
    private String comments;


}
