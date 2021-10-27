package com.hqyj.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 客户反馈表
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerFeedback extends MyPage implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 客户反馈编号
     */
    @TableId(value = "customer_feedback_id", type = IdType.AUTO)
    private Integer customerFeedbackId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 经理姓名
     */
    private String managerName;

    /**
     * 客户满意度
     */
    private String customerStatisfaction;

    /**
     * 客户反馈意见
     */
    private String customerFeedback;

    /**
     * 客户编号
     */
    private Integer customerId;

    /**
     * 经理编号
     */
    private Integer managerId;


}
