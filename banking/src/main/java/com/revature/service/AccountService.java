package com.revature.service;

import java.util.List;

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

    public List<Account> getAllAccountsByUserID(int id){
        return accountDao.getAllAccountsByUserID(id);
    }

    public List<Account> getAllAccounts(){
        return accountDao.getAllAccounts();
    }

    public Account depositInAccount(int id, float g, float s, float c){
        Account acc = accountDao.getAccountByID(id);
        accountDao.deposit(acc, g, s, c);
        return accountDao.getAccountByID(id);
    }

}
