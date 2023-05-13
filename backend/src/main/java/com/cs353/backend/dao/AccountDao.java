package com.cs353.backend.dao;

import com.cs353.backend.model.Account;
import java.util.List;

public interface AccountDao {
    List<Account> getAllAccounts();
    Account getAccountById(int id);
    int createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(int id);
}

