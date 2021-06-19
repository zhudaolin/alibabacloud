package com.zdl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhudaolin
 * @date 2021/6/1
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudSentinelClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudSentinelClientApplication.class,args);
    }
}
