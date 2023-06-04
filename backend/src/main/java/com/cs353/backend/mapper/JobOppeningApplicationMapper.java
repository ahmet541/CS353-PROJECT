package com.cs353.backend.mapper;

import com.cs353.backend.model.dto.JobOpeningApplicationDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JobOppeningApplicationMapper implements RowMapper<JobOpeningApplicationDTO> {

    @Override
    public JobOpeningApplicationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        int user_id = rs.getInt("user_id");
        int job_opening_id = rs.getInt("job_opening_id");
        Date application_date = rs.getDate("application_date");
        int application_status = rs.getInt("application_status");
        String experience = rs.getString("experience");
        String skills = rs.getString("skills");
        int education_lvl = rs.getInt("education_lvl");
        String cv = rs.getString("cv");
        return new JobOpeningApplicationDTO(user_id, job_opening_id, application_date, application_status, experience, skills, education_lvl, cv);
    }
}
