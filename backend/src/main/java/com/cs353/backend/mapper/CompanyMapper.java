package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.RegularUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        Company company = new Company();

        company.setId(rs.getInt("id"));
        company.setCompanyName(rs.getString("companyName"));
        company.setProfileDescription(rs.getString("profile_description"));
        company.setEmail(rs.getString("email"));
        company.setUserAvatar(rs.getString("avatar"));
        company.setEconomicScale(rs.getInt("economicScale"));
        company.setType(rs.getString("type"));
        return company;
    }
}