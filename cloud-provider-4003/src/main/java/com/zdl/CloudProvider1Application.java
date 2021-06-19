package com.zdl;

import com.baomidou.mybatisplus.autoconfigure.IdentifierGeneratorAutoConfiguration;
import com.zdl.config.dbconfig.MyRoutingDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhudaolin
 * @date 2021/6/1
 */

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {IdentifierGeneratorAutoConfiguration.class}) //(exclude = DruidDataSourceAutoConfigure.class)//
// 禁用druiddataresource自动装配  DruidDataSourceAutoConfigure.class,, IdentifierGeneratorAutoConfiguration.class
//@EnableTransactionManagement // 开启spring事务注解
public class CloudProvider1Application {
    public static void main(String[] args) {
        //SpringApplication.run(CloudProvider1Application.class,args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CloudProvider1Application.class, args);

    }
}
