package com.zdl.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author zhudaolin
 * @date 2021/6/30
 */
@RestController
@ResponseBody
@RequestMapping("/gateway")
public class GatewayController {

    @GetMapping("/test")
    public String test(String name){
        return "hello, " + name;
    }
}
