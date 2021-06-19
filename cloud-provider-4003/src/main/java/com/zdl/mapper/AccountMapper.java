package com.zdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdl.domain.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
