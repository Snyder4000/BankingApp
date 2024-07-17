package com.revature.service;

import java.util.ArrayList;
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

    public List<Account> getAllAccountsByUserIDAndType(int id, int type){
        List<Account> accounts = new ArrayList<>();
        switch(type){
            case 1:
                accounts = accountDao.getAllCheckingAccountsByUserID(id);
                break;
            case 2:
                accounts = accountDao.getAllSavingAccountsByUserID(id);
                break;
            case 3:
                accounts = accountDao.getAllInvestmentAccountsByUserID(id);
                break;
        }
        return accounts;
    }

    public Account getAccountByIDAndType(int id, int type){
        Account acc = new Account();
        switch(type){
            case 1:
                acc = accountDao.getCheckingAccountByID(id);
                break;
            case 2:
                acc = accountDao.getSavingAccountByID(id);
                break;
            case 3:
                acc = accountDao.getInvestmentAccountByID(id);
                break;
        }
        return acc;
    } 

    public List<Account> getAllAccounts(){
        return accountDao.getAllAccounts();
    }

    public Account depositInAccount(int type, int id, float g, float s, float c){
        Account acc = new Account();
        switch(type){
            case 1:
                acc = accountDao.getCheckingAccountByID(id);
                acc = accountDao.depositChecking(acc, g, s, c);
                break;
            case 2:
                acc = accountDao.getSavingAccountByID(id);
                acc = accountDao.depositSaving(acc, g, s, c);
                break;
            case 3:
                acc = accountDao.getInvestmentAccountByID(id);
                acc = accountDao.depositInvestment(acc, g, s, c);
                break;
        }
        return acc;
    }

    public Account withdrawFromAccount(int type, int id, float g, float s, float c){
        Account acc = new Account();
        switch(type){
            case 1:
                acc = accountDao.getCheckingAccountByID(id);
                acc = accountDao.withdrawChecking(acc, g, s, c);
                break;
            case 2:
                acc = accountDao.getSavingAccountByID(id);
                acc = accountDao.withdrawSaving(acc, g, s, c);
                break;
            case 3:
                acc = accountDao.getInvestmentAccountByID(id);
                acc = accountDao.withdrawInvestment(acc, g, s, c);
                break;
        }
        return acc;
    }

    public void deleteAccount(int type, int id){
        Account acc = new Account();
        switch(type){
            case 1:
                acc = accountDao.getCheckingAccountByID(id);
                accountDao.deleteCheckingAccount(acc);
                break;
            case 2:
                acc = accountDao.getSavingAccountByID(id);
                accountDao.deleteSavingAccount(acc);
                break;
            case 3:
                acc = accountDao.getInvestmentAccountByID(id);
                accountDao.deleteInvestmentAccount(acc);
                break;
        }
    }

}
