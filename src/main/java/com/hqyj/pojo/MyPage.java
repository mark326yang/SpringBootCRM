package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Description
 * @Autor 伍军
 * @Date 2021/10/9 14:40
 * @Version 1.0
 **/
@Data
public class MyPage {
    //页码
    //exist = false 表示不配置这个属性和表的映射关系
    @TableField(exist = false)
    private Integer page = 1;
    //每页显示条数
    @TableField(exist = false)
    private Integer row =5;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }


}
