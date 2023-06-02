package com.cs353.backend.dao.service;

import com.cs353.backend.dao.EmployDao;
import com.cs353.backend.model.dto.EmployDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@AllArgsConstructor
public class EmployDataAccessService implements EmployDao {
    private JdbcTemplate jdbcTemplate;
    @Override
    public void addEmployee(EmployDTO employDTO) {
        String sql = """
                INSERT INTO employs (company_id, regular_user_id ,recruiter_id, emp_role, emp_start_date, emp_end_date) 
                VALUES (?, ?, ?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql, employDTO.getCompany_id(), employDTO.getRegular_user_id(), employDTO.getRecruiter_id(),
                employDTO.getEmp_role(), employDTO.getEmp_start_date(), employDTO.getEmp_end_date());
    }

    @Override/**
    *It just sets the end date to today
    */
    public void deleteEmployee(int companyId, int employeeId) {
        String sql1 = """
        SELECT emp_end_date 
        FROM employs(company_id, regular_user_id ,recruiter_id, emp_role, emp_start_date, emp_end_date)  
        WHERE company_id = ? AND regular_user_id = ?
        """;
        Date endDate = jdbcTemplate.queryForObject(sql1, Date.class, companyId, employeeId);

        if(endDate == null){
            String sql2 = "UPDATE employs SET emp_end_date = CURRENT_TIMESTAMP WHERE company_id = ? AND regular_user_id = ?";

            int rowsAffected = jdbcTemplate.update(sql2, companyId, employeeId);
        }
        else{
            //we might consider to remove the employee for good temelli
        }

    }

    @Override
    public boolean isWorkingAt(int companyId, int employeeId) {
        String sql = """
                SELECT EXISTS(SELECT 1
                FROM employs
                WHERE  company_id = ? AND regular_user_id = ?) 
                """;

        return  jdbcTemplate.queryForObject(sql, Boolean.class,companyId, employeeId);
    }
}
