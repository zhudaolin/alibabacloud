package com.zdl.controller;

import com.zdl.domain.Order;
import com.zdl.mapper.OrderMapper;
import com.zdl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhudaolin
 * @date 2021/6/11
 */

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public String AddOrder(@RequestBody Order order) throws Exception {
        orderService.addOrder(order);
        return "success";
    }
}
