package com.zdl.controller.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhudaolin
 * @date 2021/6/8
 */
@Component
public class RotationLoadBalancer implements LoadBalancer {
    /**
     * 从0开始计数
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //获取单个的服务地址
    @Override
    public ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances) {
        //首先获取服务的个数
        int index = atomicInteger.incrementAndGet() % serviceInstances.size();
        //然后进行取模来轮询
        ServiceInstance serviceInstance = serviceInstances.get(index);
        //返回即可
        return serviceInstance;
    }
}
