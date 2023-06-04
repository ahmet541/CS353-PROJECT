package com.cs353.backend.dao.service;

import com.cs353.backend.dao.EmployDao;
import com.cs353.backend.mapper.PostOwnerDTOMapper;
import com.cs353.backend.model.dto.EmployDTO;
import com.cs353.backend.model.dto.PostOwnerDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
        FROM employs
        WHERE company_id = ? AND regular_user_id = ?
        """;
        Date endDate = jdbcTemplate.queryForObject(sql1, Date.class, companyId, employeeId);

        if(endDate == null){
            String sql2 = "UPDATE employs SET emp_end_date = CURRENT_TIMESTAMP WHERE company_id = ? AND regular_user_id = ?";

            int rowsAffected = jdbcTemplate.update(sql2, companyId, employeeId);
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

    @Override
    public void deleteEmployeePermanent(int companyId, Integer userId) {
        String sql = """
        Delete From employs 
        WHERE company_id = ? AND regular_user_id = ?
        """;
        jdbcTemplate.update(sql, companyId, userId);

    }

    @Override
    public int numberOfEmployees(int companyId) {
        String sql = "SELECT COUNT(*) FROM employs WHERE company_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, companyId);

        if(count == null)
            return  0;
        else
            return count;
    }

    @Override
    public List<PostOwnerDTO> getAllEmployees(int userId) {
        String sql = """
            WITH employees AS (
                SELECT regular_user_id as id
                FROM employs
                WHERE company_id = ?
            )
            SELECT P.userId, P.avatar, P.fullName
            FROM post_owner_detail P
            WHERE P.userId IN (SELECT * FROM employees);
            """;

        List<PostOwnerDTO> employees = jdbcTemplate.query(sql, new PostOwnerDTOMapper(), userId);
        return employees;

    }
}
