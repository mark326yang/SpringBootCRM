package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends MyPage implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色编号
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色类型
     */
    private String roleParam;




}
