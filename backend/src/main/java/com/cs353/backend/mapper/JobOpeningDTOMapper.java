package com.cs353.backend.mapper;

import com.cs353.backend.Enum.EmploymentStatus;
import com.cs353.backend.model.dto.JobOpeningDTO;
import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.JobOpening;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class JobOpeningDTOMapper implements RowMapper<JobOpeningDTO> {
    @Override
    public JobOpeningDTO mapRow(ResultSet rs, int rowNum ) throws SQLException{
        JobOpeningDTO jobOpeningDTO = new JobOpeningDTO();
        jobOpeningDTO.setJobOpeningId(rs.getInt("job_opening_id"));
        jobOpeningDTO.setEmploymentStatus(rs.getString("employment_status"));
        jobOpeningDTO.setExplanation( rs.getString("explanation"));
        jobOpeningDTO.setDueDate( rs.getTimestamp("due_date").toLocalDateTime());
        jobOpeningDTO.setRolePro( rs.getString("role_pro"));
        jobOpeningDTO.setLocation( rs.getString("location"));
        jobOpeningDTO.setWorkType( rs.getString("work_type"));

        try{
            jobOpeningDTO.setCompanyId(rs.getInt("company_id"));
            jobOpeningDTO.setCompanyName(rs.getString("companyname"));
        }
        catch (Exception e){
            System.out.println("problem: JobopenningDto mapper");
        }


        return jobOpeningDTO;
    }
}
