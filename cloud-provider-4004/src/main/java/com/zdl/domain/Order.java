package com.zdl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
@Data
@TableName(value = "t_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField
    private String account;

    @TableField(value = "good_id")
    private int goodId;

    @TableField
    private int count;

}
