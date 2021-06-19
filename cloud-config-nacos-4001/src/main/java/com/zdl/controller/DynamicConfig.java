package com.zdl.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author zhudaolin
 * @date 2021/6/1
 */
@Component
@Data
@RefreshScope
public class DynamicConfig {
    @Value("${myConfig.userName}")
    private String name;
    @Value("${myConfig.userAge}")
    private int age;
}
