package com.zdl.service;

import com.zdl.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cloud-provider-4003") //调用的微服务名称
@RequestMapping(value = "/cloud-provider-4003")  // url前缀
public interface Provider1Service {

    @GetMapping(value = "/providerUser/getUserJson")
    String getUserJson();

    @GetMapping(value = "/providerUser/getUserObj")
    User getUserObj();

    @GetMapping(value = "/providerUser/getUserById")
    User getUserById(@RequestParam(value = "id")Long id);

    @PostMapping(value = "/providerUser/addUser")
    User addUser(User user);

    @PostMapping(value = "/providerUser/addUserByParm")
    User addUserByParam(@RequestParam(value = "id") Long id, @RequestParam(value = "name")  String name, @RequestParam(value = "age")  Integer age);

    @PostMapping(value = "/pay/reduceAmount")
    String reduceAmount(@RequestParam(value = "account") String account, @RequestParam(value = "amount")  Integer amount);
}
