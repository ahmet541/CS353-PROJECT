package com.cs353.backend.service;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    public int createCompany(Company company) {
        return 0;//TODO
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
