package com.zdl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

/**
 * @author zhudaolin
 * @date 2021/6/1
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CloudConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerApplication.class,args);
    }

    //@LoadBalanced
    @Bean
    public RestTemplate  restTemplate(){
        return new RestTemplate();
    }
}
