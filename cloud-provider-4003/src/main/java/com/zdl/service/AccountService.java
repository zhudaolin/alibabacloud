package com.zdl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zdl.config.dbconfig.DS;
import com.zdl.domain.Account;
import com.zdl.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhudaolin
 * @date 2021/6/10
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @DS(value = "master")
    @Transactional(rollbackFor = Exception.class)
    public String reduceAmount(String account, Integer amount) throws Exception {
        checkAmount(account,amount);
        pay(account,amount);
        return "success";
    }

    private void pay(String account, Integer amount) throws Exception {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        Account account1 = accountMapper.selectOne(queryWrapper);
        if (account1.getAmount() < amount){
            throw new Exception("余额不足");
        }
    }

    private void checkAmount(String account, Integer amount) throws Exception {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        Account account1 = accountMapper.selectOne(queryWrapper);
        account1.setAmount(account1.getAmount() - amount);
        if (account1.getAmount() < amount){
            throw new Exception("余额不足");
        }
        accountMapper.updateById(account1);
    }
}
