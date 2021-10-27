package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Power extends MyPage implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 权限编号
     */
    @TableId(value = "power_id", type = IdType.AUTO)
    private Integer powerId;

    /**
     * 权限名称
     */
    private String powerName;

    /**
     * 权限类型
     */
    private String powerParam;


}
