package com.zdl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhudaolin
 * @date 2021/6/16
 */

@Data
@TableName(value = "t_good")
public class Good {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField
    private String name;

    @TableField
    private Integer price;
}
