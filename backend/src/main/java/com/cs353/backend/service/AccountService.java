package com.cs353.backend.service;

import com.cs353.backend.dao.AccountDao;
import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import com.cs353.backend.model.entities.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountDao accountDao;

    public int createAccount(Account account) {
        return accountDao.createAccount(account);
    }

    public Account getAccountById(int id) {
        return accountDao.getAccountById(id);
    }

    public int getIdByUsername(String username) {
        return accountDao.getIdByUsername(username);
    }
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(int id) {
        accountDao.deleteAccount(id);
    }

    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    public LoginResponseDTO authenticate(LoginDTO loginDTO){
        return accountDao.authenticate(loginDTO);
    }

    public boolean isEmailUsed(String email){
        return false; //TODO
    }
}
