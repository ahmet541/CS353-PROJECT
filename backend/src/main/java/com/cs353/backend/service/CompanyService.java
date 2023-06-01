package com.cs353.backend.service;

import com.cs353.backend.dao.CompanyDao;

import com.cs353.backend.model.entities.Account;

import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.cs353.backend.mapper.GeneralMapper;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyDao companyDao;
    private final GeneralMapper generalMapper;
    private final UserService userService;
    public int createCompany(Company company) {
        User user = generalMapper.mapCompanyToUser(company);
        int id = userService.createUser(user);
        company.setId(id);
        companyDao.createCompany(company);
        return id;
    }

    public Company getCompany(int userId) {
        //TODO
        return null;
    }

    public int getNumberOfEmployees(int userId) { // userId == companyId
        //TODO
        return 0;
    }

    public List<Account> getAllEmployees(int userId) {
        //TODO
        return new ArrayList<>();
    }

    public void update(int userId, Company company) {
        //TODO

    }
}
