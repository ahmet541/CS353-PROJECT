package com.cs353.backend.dao.service;

import com.cs353.backend.dao.AccountDao;
import com.cs353.backend.mapper.AccountMapper;
import com.cs353.backend.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor

public class AccountDataAccessService implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> getAllAccounts() {
        String sql = "SELECT * FROM account";

        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());

        return accounts;
    }

    @Override
    public int createAccount(Account account) {
        String sql = "INSERT INTO account (email, password) VALUES (?, ?);";
        return jdbcTemplate.update(sql, account.getEmail(), account.getPassword());
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "UPDATE account SET email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, account.getEmail(), account.getPassword(), account.getId());
    }

    @Override
    public void deleteAccount(int id) {
        String sql = "DELETE FROM account WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Account getAccountById(int id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AccountMapper(), id);
    }
}
