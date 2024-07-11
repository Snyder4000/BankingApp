package com.revature.repository;

import com.revature.entity.Account;
import java.util.List;

public interface AccountDao {
    Account createCheckingAccount(Account newAccount);
    Account createSavingAccount(Account newAccount);
    Account createInvestmentAccount(Account newAccount);
    List<Account> getAllAccounts();
    Account getAccountByID(int id);
    List<Account> getAllAccountsByUserID(int id);
    public void deposit(Account acc, float g, float s, float c);
    public void withdraw(Account acc, float g, float s, float c); 
}
