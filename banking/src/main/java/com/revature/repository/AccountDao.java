package com.revature.repository;

import com.revature.entity.Account;
import java.util.List;

public interface AccountDao {
    Account createAccount(Account newAccount);
    List<Account> getAllAccounts();  
}
