package com.cs353.backend.mapper;

import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.model.entities.CareerExpert;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDTOMapper implements RowMapper<ApplicationDTO> {
    @Override
    public ApplicationDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ApplicationDTO app = new ApplicationDTO();
        app.setApplication_status(resultSet.getInt("application_status"));
        app.setApplicationDate(resultSet.getDate("application_date"));
        app.setCv(resultSet.getString("cv"));
        app.setExperience(resultSet.getString("experience"));
        app.setEducation_lvl(resultSet.getInt("education_lvl"));
        app.setUser_id(resultSet.getInt("user_id"));
        app.setJob_opening_id(resultSet.getInt("job_opening_id"));
        app.setSkills(resultSet.getString("skills"));
        return  app;
    }
}
