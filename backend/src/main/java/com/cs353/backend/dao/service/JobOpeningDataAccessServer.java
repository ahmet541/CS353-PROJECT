package com.cs353.backend.dao.service;

import com.cs353.backend.dao.JobOpeningDao;
import com.cs353.backend.mapper.JobOpeningMapper;
import com.cs353.backend.model.entities.JobOpening;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JobOpeningDataAccessServer implements JobOpeningDao{

    private JdbcTemplate jdbcTemplate;
    @Override
    public JobOpening createJobOpening(JobOpening jobOpening, int userId) {
        String sql = """
                INSERT INTO JobOpening (employment_status, explanation, due_date, role_pro, location, work_type)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING job_opening_id
                """;
        int id = jdbcTemplate.queryForObject(sql, Integer.class, jobOpening.getEmploymentStatus(), jobOpening.getExplanation(), jobOpening.getDueDate(), jobOpening.getRolePro(), jobOpening.getLocation(), jobOpening.getWorkType() );
        jobOpening.setJobOpeningID(id);
        return jobOpening;
    }

    @Override
    public List<JobOpening> getAllJobOpenings() {
        String sql = """
                SELECT *
                FROM jobopening
                ORDER BY due_date
                """;
        return = jdbcTemplate.query(sql, new JobOpeningMapper());
    }
}
