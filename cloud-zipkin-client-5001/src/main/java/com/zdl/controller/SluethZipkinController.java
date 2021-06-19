package com.zdl.controller;

import com.zdl.User;
import com.zdl.service.ConsumerFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhudaolin
 * @date 2021/6/18
 */

@RestController
@RequestMapping(value = "/slueth-zipkin")
public class SluethZipkinController {

    @Autowired
    private ConsumerFeignService consumerFeignService;

    @GetMapping(value = "/getUserJson")
    public String getUserJson(){
        return consumerFeignService.getUserJson();
    }

    @GetMapping(value = "/getUserObj")
    public User getUserObj(){
        return consumerFeignService.getUserObj();
    }

    @GetMapping(value = "/getUserById")
    public User getUserById(@RequestParam Long id){
        return consumerFeignService.getUserById(id);
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User user){
        return consumerFeignService.addUser(user);
    }

    @PostMapping(value = "/addUserByParam")
    public User addUserByParam(@RequestParam Long id, @RequestParam String name, @RequestParam Integer age){
        return consumerFeignService.addUserByParam(id,name,age);
    }
}
