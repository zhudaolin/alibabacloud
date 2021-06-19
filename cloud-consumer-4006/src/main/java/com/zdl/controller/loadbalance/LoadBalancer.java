package com.zdl.controller.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 手写负载均衡算法
 */
public interface LoadBalancer {

    /**
     * 根据多个不同的地址 返回单个调用rpc地址
     *
     * @param serviceInstances
     * @return
     */
    ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances);
}
