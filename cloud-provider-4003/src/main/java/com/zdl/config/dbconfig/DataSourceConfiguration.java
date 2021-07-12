package com.zdl.config.dbconfig;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
@Configuration
public class DataSourceConfiguration {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    /**
     * 注册DruidServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "root");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        return servletRegistrationBean;
    }

    /**
     * 注册DruidFilter拦截
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<String, String>();
        //设置忽略请求
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    /**
     * 默认是数据源
     */
    @Value("${spring.datasource.druid.defaultDs}")
    private String defaultDs;

    @Bean(name = "myRoutingDataSource")
    public MyRoutingDataSource dataSource(@Qualifier("dataSourceMaster") DataSource dataSourceMaster, @Qualifier("dataSourceSlave") DataSource dataSourceSlave) {
        MyRoutingDataSource dynamicDataSource = new MyRoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put(DsTypeEnum.DS_MASTER.getDesc(), dataSourceMaster);
        targetDataResources.put(DsTypeEnum.DS_SLAVE.getDesc(), dataSourceSlave);
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);
        dynamicDataSource.setTargetDataSources(targetDataResources);
        DbContextHolder.setDefaultDs(defaultDs);
        return dynamicDataSource;
    }

    /**
     * 事务配置
     * @return
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(applicationContext.getBean(MyRoutingDataSource.class));
        return transactionManager;
    }
}
