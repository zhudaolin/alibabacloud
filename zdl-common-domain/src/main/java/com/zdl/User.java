package com.zdl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhudaolin
 * @date 2021/6/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private Integer age;
}
