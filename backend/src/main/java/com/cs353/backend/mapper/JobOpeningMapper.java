package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.JobOpening;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class JobOpeningMapper implements RowMapper<JobOpening> {
    @Override
    public JobOpening mapRow(ResultSet rs, int rowNum ) throws SQLException{
        int jobOpeningID = rs.getInt("job_opening_id");
        int employmentStatus = rs.getInt("employment_status");
        String explanation = rs.getString("explanation");
        LocalDateTime dueDate = rs.getTimestamp("due_date").toLocalDateTime();
        String rolePro = rs.getString("role_pro");
        String location = rs.getString("location");
        String workType = rs.getString("work_type");

        return new JobOpening(jobOpeningID, employmentStatus, explanation,dueDate, rolePro, location, workType );
    }
}
