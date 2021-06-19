package com.zdl.config.dbconfig;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
@Configuration
public class DataSourceMasterConfig {

    @Value("${spring.datasource.druid.master.url}")
    private String url;
    @Value("${spring.datasource.druid.master.username}")
    private String username;
    @Value("${spring.datasource.druid.master.password}")
    private String password;
    @Value("${spring.datasource.druid.master.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.druid.master.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.druid.master.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.druid.master.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.druid.master.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.druid.master.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.druid.master.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;
    @Value("${spring.datasource.druid.master.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.druid.master.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.master.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.druid.master.validationQueryTimeout}")
    private int validationQueryTimeout;
    @Value("${spring.datasource.druid.master.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.druid.master.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.druid.master.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.druid.master.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${spring.datasource.druid.master.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;
    @Value("${spring.datasource.druid.master.filters}")
    private String filters;
    @Value("${spring.datasource.druid.master.connectionInitSqls}")
    private List<String> connectionInitSqls;


    @Bean(name = "dataSourceMaster")
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
        DbContextHolder.addDataSource(DsTypeEnum.DS_MASTER.getDesc(), druidDataSource);
        return druidDataSource;
    }

    @Bean("masterStatFilter")
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true); //slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
        statFilter.setMergeSql(true); //SQL合并配置
        statFilter.setSlowSqlMillis(1000);//slowSqlMillis的缺省值为3000，也就是3秒。
        return statFilter;
    }


    @Bean("masterWallFilter")
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


}
