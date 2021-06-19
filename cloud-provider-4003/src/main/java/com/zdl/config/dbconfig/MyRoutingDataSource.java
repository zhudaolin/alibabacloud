package com.zdl.config.dbconfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getCurrentDsStr();
    }
}