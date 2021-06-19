package com.zdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdl.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
