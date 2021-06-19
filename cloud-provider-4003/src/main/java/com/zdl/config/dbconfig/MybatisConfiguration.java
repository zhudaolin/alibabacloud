package com.zdl.config.dbconfig;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
@Slf4j
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@MapperScan(basePackages = {"com.zdl.mapper"})
public class MybatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "myRoutingDataSource") MyRoutingDataSource myRoutingDataSource) throws
            Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mybatisSqlSessionFactoryBean")
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier(value = "myRoutingDataSource") DataSource dataSource) throws Exception {
        log.info("==========>开始注入 MybatisSqlSessionFactoryBean");
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        Set<Resource> result = new LinkedHashSet<>(16);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            result.addAll(Arrays.asList(resolver.getResources("classpath*:mybatis/mapper/*.xml")));
            result.addAll(Arrays.asList(resolver.getResources("classpath*:mybatis/*/*.xml")));
        } catch (IOException e) {
            log.error("获取【classpath:mapper/*/*.xml,classpath:config/mapper/*/*.xml】资源错误!异常信息:{}", e);
        }
        bean.setMapperLocations(result.toArray(new org.springframework.core.io.Resource[0]));
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setMapUnderscoreToCamelCase(true);
        //添加 乐观锁插件
        //configuration.addInterceptor(new OptimisticLockerInnerInterceptor());
        bean.setConfiguration(configuration);
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        //设置 字段自动填充处理
        //globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        bean.setGlobalConfig(globalConfig);
        log.info("==========>注入 MybatisSqlSessionFactoryBean 完成!");
        return bean;
    }

}