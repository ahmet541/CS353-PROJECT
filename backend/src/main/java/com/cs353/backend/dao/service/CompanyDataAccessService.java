package com.cs353.backend.dao.service;

import com.cs353.backend.dao.CompanyDao;
import com.cs353.backend.model.entities.Company;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
@AllArgsConstructor
public class CompanyDataAccessService implements CompanyDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void createCompany(@NotNull Company company) {
        String sql = """
                    INSERT INTO Company (id, companyName, type ,economicScale) 
                    VALUES (?, ?, ?, ?);
                    """;

        jdbcTemplate.update(sql,company.getId(), company.getCompanyName(), company.getType(), company.getEconomicScale());
    }
}
