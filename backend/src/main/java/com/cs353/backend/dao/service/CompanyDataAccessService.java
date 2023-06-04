package com.cs353.backend.dao.service;

import com.cs353.backend.dao.CompanyDao;
import com.cs353.backend.mapper.CompanyMapper;
import com.cs353.backend.model.entities.Company;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Override
    public List<Company> getAllCompanies() {

        return null;
    }

    @Override
    public Company getCompanyById(int id) {
        String sql = """
                 SELECT C.*, U.profile_description, U.avatar, A.email 
                 FROM Company C 
                 JOIN "User" U ON U.id = C.id
                 JOIN Account A ON A.id = C.id
                 WHERE C.id = ?   
                """;
        return jdbcTemplate.queryForObject(sql, new CompanyMapper(), id);
    }

    @Override
    public void updateCompany(Company company) {
        String sql = """
        UPDATE Company
        SET companyname = ?, economicScale = ?, type = ? 
        WHERE id = ?;
        """;
        jdbcTemplate.update(sql, company.getCompanyName(), company.getEconomicScale(), company.getType(), company.getId());
    }

    @Override
    public void deleteCompany(int id) {

    }

    public void verifyRecruiter(int companyId, int recruiterId){
        String sql = """
                    INSERT INTO verifies (company_id, recruiter_id) 
                    VALUES (?, ?);
                    """;

        jdbcTemplate.update(sql, companyId, recruiterId);
    }

    @Override
    public int isRecruiterVerified(int userid) {

        String sql = """
                SELECT V.company_id
                FROM verifies V
                WHERE V.recruiter_id = ?
                """;
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, userid);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("The recruiter is not in the verified table: " + e.getMessage());
        }
        return -1; // Error value
    }

    @Override
    public int countEmployees(int userid) {


        String sql = """
            SELECT COUNT(DISTINCT regular_user_id) AS distinctEmployeeCount
            FROM employs
            WHERE company_id = ?
            """;


        return jdbcTemplate.queryForObject(sql, Integer.class, userid);
    }

}
