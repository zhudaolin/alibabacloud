package com.zdl.controller;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhudaolin
 * @date 2021/5/31
 */
@RestController
@RequestMapping("/config")
public class TestConstroller {

    @Autowired
    private DynamicConfig dynamicConfig;

    @GetMapping("/test")
    public String getUser(){
        System.out.println("/config/test");
        Map<String,Object> user = new HashMap<>();
        user.put("userName", dynamicConfig.getName());
        user.put("userAge", dynamicConfig.getAge());
        return new Gson().toJson(user);
    }
}
