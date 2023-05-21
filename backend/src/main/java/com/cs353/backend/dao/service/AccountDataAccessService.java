package com.cs353.backend.dao.service;

import com.cs353.backend.Enum.UserRole;
import com.cs353.backend.dao.AccountDao;
import com.cs353.backend.mapper.AccountMapper;
import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import com.cs353.backend.model.entities.Account;
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
    public Account getAccountById(int id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AccountMapper(), id);
    }
    @Override
    public int getIdByUsername(String email) {
        String sql = "SELECT id FROM account WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }
    @Override
    public int createAccount(Account account) {
        String sql = "INSERT INTO account (email, password) VALUES (?, ?);";
        jdbcTemplate.update(sql, account.getEmail(), account.getPassword());
        return getIdByUsername(account.getEmail());
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
    public LoginResponseDTO authenticate(LoginDTO loginDTO) {
        String sql = "SELECT id FROM Account WHERE email = ? AND password = ?";
        Integer accountId = jdbcTemplate.queryForObject(sql, Integer.class, loginDTO.getEmail(), loginDTO.getPassword());

        if(accountId != null){
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setUserId(accountId);
            loginResponseDTO.setRole(null);
            UserRole role = getRole(accountId);
            loginResponseDTO.setRole(role);

            return loginResponseDTO;
        }
        else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }
    private UserRole getRole(Integer accountId) {
        String adminSql = "SELECT EXISTS(SELECT 1 FROM Admin WHERE id = ?)";
        boolean isAdmin = jdbcTemplate.queryForObject(adminSql, Boolean.class, accountId);
        if (isAdmin) {
            return UserRole.ADMIN;
        }

        // Check if the user is a company
        String companySql = "SELECT EXISTS(SELECT 1 FROM Company WHERE id = ?)";
        boolean isCompany = jdbcTemplate.queryForObject(companySql, Boolean.class, accountId);
        if (isCompany) {
            return UserRole.COMPANY;
        }

        String careerExpertSql = "SELECT EXISTS(SELECT 1 FROM Career_Expert WHERE id = ?)";
        boolean isCareerExpert = jdbcTemplate.queryForObject(careerExpertSql, Boolean.class, accountId);
        if (isCareerExpert) {
            return UserRole.CAREER_EXPERT;
        }

        // Check if the user is a recruiter
        String recruiterSql = "SELECT EXISTS(SELECT 1 FROM Recruiter WHERE id = ?)";
        boolean isRecruiter = jdbcTemplate.queryForObject(recruiterSql, Boolean.class, accountId);
        if (isRecruiter) {
            return UserRole.RECRUITER;
        }

        // Check if the user is a regular user
        String regularUserSql = "SELECT EXISTS(SELECT 1 FROM Regular_User WHERE id = ?)";
        boolean isRegularUser = jdbcTemplate.queryForObject(regularUserSql, Boolean.class, accountId);
        if (isRegularUser) {
            return UserRole.REGULAR_USER;
        }

        // Check if the user is a regular user
        String accountSql = "SELECT EXISTS(SELECT 1 FROM Account WHERE id = ?)";
        boolean isAccount = jdbcTemplate.queryForObject(accountSql, Boolean.class, accountId);
        if (isAccount) {
            return UserRole.ACCOUNT;
        }

        // Handle case when the user ID is not found in any of the tables
        throw new IllegalArgumentException("User ID not found or unknown role");
    }



}
