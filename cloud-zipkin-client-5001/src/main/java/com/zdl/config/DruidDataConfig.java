package com.zdl.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhudaolin
 * @date 2021/6/11
 */
@Configuration
public class DruidDataConfig {

    @Value("${spring.datasource.druid.url}")
    private String url;
    @Value("${spring.datasource.druid.username}")
    private String username;
    @Value("${spring.datasource.druid.password}")
    private String password;
    @Value("${spring.datasource.druid.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.druid.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;
    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.druid.validationQueryTimeout}")
    private int validationQueryTimeout;
    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.druid.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${spring.datasource.druid.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;
    @Value("${spring.datasource.druid.filters}")
    private String filters;
    @Value("${spring.datasource.druid.connectionInitSqls}")
    private List<String> connectionInitSqls;

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
        servletRegistrationBean.addInitParameter("loginPassword", "1566023_Zan");
        return servletRegistrationBean;
    }

    @Bean(name = "druidDataSource")
    public DruidDataSource dataSourceMaster() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setValidationQueryTimeout(validationQueryTimeout);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setFilters(filters);
        druidDataSource.setConnectionInitSqls(connectionInitSqls);
        //sql监控
        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter());
        filters.add(wallFilter());
        druidDataSource.setProxyFilters(filters);
        return druidDataSource;
    }
    @Bean("statFilter")
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true); //slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
        statFilter.setMergeSql(true); //SQL合并配置
        statFilter.setSlowSqlMillis(1000);//slowSqlMillis的缺省值为3000，也就是3秒。
        return statFilter;
    }


    @Bean("wallFilter")
    public WallFilter wallFilter() {

        WallFilter wallFilter = new WallFilter();
        wallFilter.setLogViolation(true);//对被认为是攻击的SQL进行LOG.error输出
        wallFilter.setThrowException(true);//对被认为是攻击的SQL抛出SQLExcepton

        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);//允许执行多条SQL
        config.setDeleteWhereNoneCheck(true);//不允许无where删除语句执行
        config.setUpdateWhereNoneCheck(true);//不允许无where更新语句执行
        wallFilter.setConfig(config);
        return wallFilter;
    }

    /**
     * 事务配置
     * @return
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(applicationContext.getBean(DruidDataSource.class));
        return transactionManager;
    }
}
