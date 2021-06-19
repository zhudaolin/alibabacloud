package com.zdl.controller;

import com.google.gson.Gson;
import com.zdl.User;
import com.zdl.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhudaolin
 * @date 2021/6/1
 */
@RestController
@RequestMapping("/providerUser")
public class ProviderController {

    @Autowired
    IuserService userService;

    @GetMapping("/getUserJson")
    public String getUserJson(){
        return new Gson().toJson(userService.queryUser());
    }

    @GetMapping("/getUserObj")
    public User getUserObj(){
        return userService.queryUser();
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam(value = "id") Long id){
        return userService.queryUserById(id);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/addUserByParm")
    public User addUserByParm(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("age") Integer age){
        System.out.println(System.getProperty("server.port"));
        return userService.constructUser(id,name,age);
    }

}
