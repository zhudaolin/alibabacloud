package com.zdl.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhudaolin
 * @date 2021/6/1
 */
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    @GetMapping("/user")
    //@SentinelResource(value = "getUser", blockHandler = "exceptionHandler", fallback = "helloFallback")
    //sentinel默认对所有http服务进行了限流埋点，无需注解。service埋点需要手工注解埋点
    public String getUser(){
        Map<String,Object> result = new HashMap<>();
        result.put("userName","zhudaolin");
        result.put("userAge",18);
        return new Gson().toJson(result);
    }


}
