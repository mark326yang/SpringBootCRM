package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zyh
 * @version 1.0
 * @items
 * @Date:on 2021/10/22 at 10:27
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Customer extends MyPage implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 客户编号
     */
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;

    //客户名字
    private String customerName;

    //客户性别
    private String customerSex;

    //客户生日

    private Date customerBirthday;

    //客户电话
    private String customerPhone;

    //客户邮件
    private String customerEmail;

    //客户学历
    private String customerEducation;

    //客户身份证
    private String customerIdnumber;

    //客户单位

    private String customerUnit;

    //联系人
    private String customerLinkman;

    //备注
    private String comments;

    //经理编号
    private Integer managerId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getCustomerBirthday() {
        return customerBirthday;
    }


    public void setCustomerBirthday(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(birthday != null){
            try {

                this.customerBirthday = sdf.parse(birthday);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.customerBirthday = new Date();
        }

    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEducation() {
        return customerEducation;
    }

    public void setCustomerEducation(String customerEducation) {
        this.customerEducation = customerEducation;
    }

    public String getCustomerIdnumber() {
        return customerIdnumber;
    }

    public void setCustomerIdnumber(String customerIdnumber) {
        this.customerIdnumber = customerIdnumber;
    }

    public String getCustomerUnit() {
        return customerUnit;
    }

    public void setCustomerUnit(String customerUnit) {
        this.customerUnit = customerUnit;
    }

    public String getCustomerLinkman() {
        return customerLinkman;
    }

    public void setCustomerLinkman(String customerLinkman) {
        this.customerLinkman = customerLinkman;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerSex='" + customerSex + '\'' +
                ", customerBirthday=" + customerBirthday +
                '}';
    }
}
