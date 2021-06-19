package com.zdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdl.domain.Good;
import com.zdl.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodMapper extends BaseMapper<Good> {

}
