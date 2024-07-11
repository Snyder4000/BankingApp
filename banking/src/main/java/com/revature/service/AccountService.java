package com.revature.service;

import com.revature.repository.AccountDao;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(AccountDao dao){
        this.accountDao = dao;
    }
}
