package com.zdl.controller;

import com.zdl.service.SeataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhudaolin
 * @date 2021/6/16
 */
@RestController
@RequestMapping(value = "/seata")
public class SeataController {

    @Autowired
    private SeataService seataService;

    @PostMapping(value = "/addOrder")
    public String addOrder(@RequestParam(value = "account") String account, @RequestParam(value = "goodId") Long goodId, @RequestParam(value = "count") int count){
        return seataService.addOrder(account,goodId,count);
    }
}
