package com.zdl.config.dbconfig;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
@Order(0)
@Aspect
@Component
@Slf4j
public class DsAspect {
    /**
     * 配置AOP切面的切入点
     * 切换放在service接口的方法上
     */
    @Pointcut("execution(* com.zdl..service..*.*(..))")
    public void dataSourcePointCut() {
    }

    /**
     * 根据切点信息获取调用函数是否用TargetDataSource切面注解描述，
     * 如果设置了数据源，则进行数据源切换
     */
    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        if (StringUtils.isNotBlank(DbContextHolder.getCurrentDsStr())) {
            log.info("==========>current thread {} use dataSource[{}]", Thread.currentThread().getName(), DbContextHolder.getCurrentDsStr());
            return;
        }
        String methodName = joinPoint.getSignature().getName();
        // 自定义注解在方法级别， 也可以改到类级别上
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            if (null != method && method.isAnnotationPresent(DS.class)) {
                // 根据注解 切换数据源
                DS td = method.getAnnotation(DS.class);
                String dbStr = td.value();
                DbContextHolder.setCurrentDsStr(dbStr);
                log.info("==========>current thread {} add dataSource[{}] to ThreadLocal, request method name is : {}",
                        Thread.currentThread().getName(), dbStr, methodName);
            } else {
                DbContextHolder.setCurrentDsStr(DbContextHolder.getDefaultDs());
                log.info("==========>use default datasource[{}] , request method name is :  {}",
                        DbContextHolder.getDefaultDs(), methodName);
            }
        } catch (Exception e) {
            log.error("==========>current thread {} add data to ThreadLocal error,{}", Thread.currentThread().getName(), e);
            throw e;
        }
    }


    /**
     * 执行完切面后，将线程共享中的数据源名称清空，
     * 数据源恢复为原来的默认数据源
     */
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("==========>clean datasource[{}]", DbContextHolder.getCurrentDsStr());
        DbContextHolder.clearCurrentDsStr();
    }
}