package com.zdl.service;

import com.zdl.domain.Order;
import com.zdl.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhudaolin
 * @date 2021/6/15
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Transactional(rollbackFor = Exception.class)
    public void addOrder(Order order) throws Exception {
        orderMapper.insert(order);
    }
}
