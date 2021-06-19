package com.zdl;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhudaolin
 * @date 2021/5/31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudConfigNacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigNacosApplication.class, args);
        /*ConfigurableApplicationContext applicationContext = SpringApplication.run(CloudConfigNacosApplication.class, args);
        while(true){
            String userName = applicationContext.getEnvironment().getProperty("myConfig.userName");
            String userAge = applicationContext.getEnvironment().getProperty("myConfig.userAge");
            System.err.println("user name :"+userName+"; age: "+userAge);

            ThreadUtil.sleep(5000);
        }*/
    }
}
