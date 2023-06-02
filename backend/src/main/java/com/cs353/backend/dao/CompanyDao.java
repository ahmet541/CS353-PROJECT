package com.cs353.backend.dao;

import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.RegularUser;

import java.util.List;

public interface CompanyDao {

    void createCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyById(int id);
    void updateCompany(Company company);
    void deleteCompany(int id);

    void verifyRecruiter(int companyId, int recruiterId);
    int isRecruiterVerified(int userid);
}
