package com.zdl.controller;

import com.zdl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhudaolin
 * @date 2021/6/9
 */
@RestController
@RequestMapping(value = "/pay")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/reduceAmount")
    public String reduceAmount(@RequestParam("account") String account, @RequestParam("amount") Integer amount) throws Exception {
        return accountService.reduceAmount(account, amount);
    }


}
