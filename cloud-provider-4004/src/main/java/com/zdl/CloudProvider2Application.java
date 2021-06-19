package com.zdl;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.IdentifierGeneratorAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhudaolin
 * @date 2021/6/1
 */
@SpringBootApplication (exclude = {IdentifierGeneratorAutoConfiguration.class})
// DataSourceAutoConfiguration.class
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(value = "com.zdl.mapper")
public class CloudProvider2Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudProvider2Application.class,args);
    }
}
