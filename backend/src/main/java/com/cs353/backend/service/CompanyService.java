package com.cs353.backend.service;

import com.cs353.backend.dao.CompanyDao;

import com.cs353.backend.model.dto.PostOwnerDTO;
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
    private final RecruiterService recruiterService;

    public int createCompany(Company company) {
        User user = generalMapper.mapCompanyToUser(company);
        int id = userService.createUser(user);
        company.setId(id);
        companyDao.createCompany(company);
        return id;
    }

    public Company getCompany(int userId) {
        return companyDao.getCompanyById(userId);
    }

    public int getNumberOfEmployees(int userId) { // userId == companyId
        //TODO
        return 0;
    }

    public List<PostOwnerDTO> getAllEmployees(int userId) {
        //TODO
        // type should be PostOwnerDTO, do not bother with name, this DTO includes id,fullName, and avatar
        // See same methods for getAllFollowers() getAllConnections()
        return new ArrayList<>();
    }

    public void update(int userId, Company company) {
        company.setId(userId);
        User user = generalMapper.mapCompanyToUser(company);
        user.setId(userId);
        userService.updateUser(user);
        companyDao.updateCompany(company);

    }

    public void verifyRecruiter(int companyId, int recruiterId){

        recruiterService.addRecruiter(recruiterId);
        companyDao.verifyRecruiter(companyId, recruiterId);

    }



}
