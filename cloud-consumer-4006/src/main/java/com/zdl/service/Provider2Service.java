package com.zdl.service;

import com.zdl.User;
import com.zdl.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "cloud-provider-4004") //调用的微服务名称
@RequestMapping(value = "cloud-provider-4004") // url前缀
public interface Provider2Service {

    @RequestMapping(value = "/Order/addOrder", method = RequestMethod.POST, consumes = "application/json")
    String addOrder(@RequestBody Order order);

}
