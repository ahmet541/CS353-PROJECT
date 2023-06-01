package com.cs353.backend.dao.service;

import com.cs353.backend.Enum.EmploymentStatus;
import com.cs353.backend.dao.JobOpeningDao;
import com.cs353.backend.mapper.JobOpeningMapper;
import com.cs353.backend.model.dto.JobOpeningDTO;
import com.cs353.backend.model.entities.JobOpening;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        int id = jdbcTemplate.queryForObject(sql, Integer.class, Enum.valueOf(EmploymentStatus.class, jobOpening.getEmploymentStatus()).getValue(), jobOpening.getExplanation(), jobOpening.getDueDate(), jobOpening.getRolePro(), jobOpening.getLocation(), jobOpening.getWorkType() );
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
        return jdbcTemplate.query(sql, new JobOpeningMapper());
    }

    @Override
    public List<JobOpening> getJobOpeningsByFilter(JobOpeningDTO jobOpeningDTO) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM your_table WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (jobOpeningDTO.getEmploymentStatus() != null) {
            queryBuilder.append(" AND employment_status = ?");
            params.add(Enum.valueOf(EmploymentStatus.class, jobOpeningDTO.getEmploymentStatus().toUpperCase()).getValue());
        }

        if (jobOpeningDTO.getExplanation() != null) {
            queryBuilder.append(" AND explanation = ?");
            params.add(jobOpeningDTO.getExplanation());
        }

        if (jobOpeningDTO.getDueDate() != null) {
            queryBuilder.append(" AND due_date = ?");
            params.add(jobOpeningDTO.getDueDate());
        }

        if(jobOpeningDTO.getRolePro() != null) {
            queryBuilder.append(" AND role_pro = ?");
            params.add(jobOpeningDTO.getRolePro());
        }

        if(jobOpeningDTO.getLocation() != null) {
            queryBuilder.append(" AND location = ?");
            params.add(jobOpeningDTO.getLocation());
        }

        if(jobOpeningDTO.getWorkType() != null) {
            queryBuilder.append(" AND work_type = ?");
            params.add(jobOpeningDTO.getWorkType());
        }

        return jdbcTemplate.query(queryBuilder.toString(), new JobOpeningMapper(), params.toArray());
    }
}
