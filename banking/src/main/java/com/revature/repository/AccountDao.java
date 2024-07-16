package com.revature.repository;

import com.revature.entity.Account;
import java.util.List;

public interface AccountDao {
    Account createCheckingAccount(Account newAccount);
    Account createSavingAccount(Account newAccount);
    Account createInvestmentAccount(Account newAccount);
    List<Account> getAllAccounts();
    Account getCheckingAccountByID(int id);
    Account getSavingAccountByID(int id);
    Account getInvestmentAccountByID(int id);
    List<Account> getAllCheckingAccountsByUserID(int id);
    List<Account> getAllSavingAccountsByUserID(int id);
    List<Account> getAllInvestmentAccountsByUserID(int id);
    public Account depositChecking(Account acc, float g, float s, float c);
    public Account depositSaving(Account acc, float g, float s, float c);
    public Account depositInvestment(Account acc, float g, float s, float c);
    public Account withdrawChecking(Account acc, float g, float s, float c);
    public Account withdrawSaving(Account acc, float g, float s, float c);
    public Account withdrawInvestment(Account acc, float g, float s, float c);
    public void deleteCheckingAccount(Account acc);
    public void deleteSavingAccount(Account acc); 
    public void deleteInvestmentAccount(Account acc);  
}
