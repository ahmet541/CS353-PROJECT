package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.Recruiter;
import com.cs353.backend.model.entities.RegularUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecruiterMapper implements RowMapper<Recruiter> {
    @Override
    public Recruiter mapRow(ResultSet rs, int rowNum) throws SQLException {

        Recruiter recruiter = new Recruiter();
        recruiter.setRecruting_start_date(rs.getDate("recruiting_start_date"));
        return recruiter;
    }
}
