package com.zdl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String account;
    private long goodId;
    private int count;

}
