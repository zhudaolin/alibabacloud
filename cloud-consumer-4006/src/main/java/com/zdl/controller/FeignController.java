package com.zdl.controller;

import com.zdl.User;
import com.zdl.service.Provider1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhudaolin
 * @date 2021/6/16
 */
@RestController
@RequestMapping(value = "/feign")
public class FeignController {

    @Autowired
    private Provider1Service providerService;

    @GetMapping(value = "/getUserJson")
    public String getUserJson(){
        return providerService.getUserJson();
    }

    @GetMapping(value = "/getUserObj")
    public User getUserObj(){
        return providerService.getUserObj();
    }

    @GetMapping(value = "/getUserById")
    public User getUserById(@RequestParam Long id){
        return providerService.getUserById(id);
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User user){
        return providerService.addUser(user);
    }

    @PostMapping(value = "/addUserByParam")
    public User addUserByParam(@RequestParam Long id, @RequestParam String name, @RequestParam Integer age){
        return providerService.addUserByParam(id,name,age);
    }
}
