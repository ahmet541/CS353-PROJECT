package com.cs353.backend.dao.service;

import com.cs353.backend.dao.OpenPositionDao;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OpenPositionDataAccessService implements OpenPositionDao {
    private JdbcTemplate jdbcTemplate;
    @Override
    public void addOpenPosition(int companyId, int recruiterId, int jobOpeningId) {
        String sql = """
                INSERT INTO open_position (company_id, recruiter_id, job_opening_id)
                VALUES (?, ?, ?)
                """;
                jdbcTemplate.update(sql, companyId, recruiterId, jobOpeningId);
    }

     @Override
    public int getCompanyId(int jobOpeningID){
        String sql = """
                SELECT company_id
                FROM open_position
                WHERE job_opening_id = ?
                """;
        return jdbcTemplate.queryForObject(sql, Integer.class, jobOpeningID);
    }
}
