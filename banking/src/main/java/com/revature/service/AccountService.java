package com.revature.service;

import com.revature.entity.Account;
import com.revature.repository.AccountDao;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(AccountDao dao){
        this.accountDao = dao;
    }

    public Account createNewCheckingAccount(Account newAccount){
        return accountDao.createCheckingAccount(newAccount);
    }

    public Account createNewSavingAccount(Account newAccount){
        return accountDao.createSavingAccount(newAccount);
    }

    public Account createNewInvestmentAccount(Account newAccount){
        return accountDao.createInvestmentAccount(newAccount);
    }

}
