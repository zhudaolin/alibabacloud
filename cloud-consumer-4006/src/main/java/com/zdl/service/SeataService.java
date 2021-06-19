package com.zdl.service;

import com.zdl.domain.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhudaolin
 * @date 2021/6/16
 */
@Service
public class SeataService {

    @Autowired
    private Provider1Service provider1Service;

    @Autowired
    private Provider2Service provider2Service;

    @GlobalTransactional
    public String addOrder(String account, Long goodId, int count) {
        int price = 100;
        Order order = new Order(null,account,goodId,count);
        provider2Service.addOrder(order);
        provider1Service.reduceAmount(account,count*price);
        return "success";
    }
}
