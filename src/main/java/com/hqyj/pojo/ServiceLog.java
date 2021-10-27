package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 服务记录表
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ServiceLog extends MyPage implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 服务编号
     */
    @TableId(value = "service_id", type = IdType.AUTO)
    private Integer serviceId;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 服务预估成本
     */
    private Double estimateCost;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getServiceStartdate() {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //try {
        //    managerBirthday = sdf.parse(managerBirthday);
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //}
        return serviceStartdate;
    }

    public void setServiceStartdate(String serviceStartdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(serviceStartdate!= null){
            try {
                this.serviceStartdate = sdf.parse(serviceStartdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            this.serviceStartdate = new Date();
        }
    }
    /**
     * 服务开始日期
     */
    private Date serviceStartdate;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getServiceEnddate() {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //try {
        //    managerBirthday = sdf.parse(managerBirthday);
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //}
        return serviceEnddate;
    }

    public void setServiceEnddate(String serviceEnddate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(serviceEnddate != null && serviceEnddate!=""){
            try {
                this.serviceEnddate = sdf.parse(serviceEnddate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            this.serviceEnddate = new Date();
        }
    }

    /**
     * 服务结束日期
     */
    private Date serviceEnddate;

    /**
     * 服务内容描述
     */
    private String serviceContent;

    /**
     * 服务状态
     */
    private String serviceState;

    /**
     * 经理编号
     */
    private Integer managerId;


}
