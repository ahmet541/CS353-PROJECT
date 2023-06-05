package com.cs353.backend.dao.service;

import com.cs353.backend.dao.AdminDao;
import com.cs353.backend.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AdminDataAccessService implements AdminDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> numberOfEmployeesAndRecruiters() {
        String sql = """
        SELECT E.company_id, COUNT(DISTINCT E.regular_user_id) AS numEmployees, COUNT(DISTINCT E.recruiter_id) AS numRecruiters, P.fullname
        FROM employs E
        INNER JOIN post_owner_detail P ON E.company_id = P.userid
        GROUP BY E.company_id, P.fullname
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int companyId = rs.getInt("company_id");
            int numEmployees = rs.getInt("numEmployees");
            int numRecruiters = rs.getInt("numRecruiters");
            String companyName = rs.getString("fullname");

            Map<String, Object> data = new HashMap<>();
            data.put("companyId", companyId);
            data.put("numEmployees", numEmployees);
            data.put("numRecruiters", numRecruiters);
            data.put("companyName", companyName);

            return data;
        });
    }

    @Override
    public List<Map<String, Object>> getRecruiterEmployeeCounts() {
        String sql = """
        SELECT E.recruiter_id, P.fullName, COUNT(DISTINCT E.regular_user_id) AS numEmployees
        FROM employs E
        INNER JOIN post_owner_detail P ON E.recruiter_id = P.userId
        WHERE E.recruiter_id IS NOT NULL
        GROUP BY E.recruiter_id, P.fullName
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int recruiterId = rs.getInt("recruiter_id");
            String recruiterName = rs.getString("fullName");
            int employeeCount = rs.getInt("numEmployees");

            Map<String, Object> data = new HashMap<>();
            data.put("recruiterId", recruiterId);
            data.put("recruiterName", recruiterName);
            data.put("employeeCount", employeeCount);

            return data;
        });
    }

    @Override
    public void createAdmin(User admin, String name) {
        String sql = "INSERT INTO admin (id,admin_name) VALUES (?, ?);";
        jdbcTemplate.update(sql, admin.getId(), name);
    }
}
