package com.zdl.service;

import com.zdl.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cloud-consumer-4006") //调用的微服务名称
@RequestMapping(value = "/cloud-consumer-4006")
public interface ConsumerFeignService {

    @GetMapping(value = "/feign/getUserJson")
    String getUserJson();

    @GetMapping(value = "/feign/getUserObj")
    User getUserObj();

    @GetMapping(value = "/feign/getUserById")
    User getUserById(@RequestParam(value = "id") Long id);

    @PostMapping(value = "/feign/addUser")
    User addUser(User user);

    @PostMapping(value = "/feign/addUserByParm")
    User addUserByParam(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age);

}
