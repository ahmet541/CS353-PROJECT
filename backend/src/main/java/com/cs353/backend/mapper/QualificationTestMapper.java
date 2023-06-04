package com.cs353.backend.mapper;

import com.cs353.backend.model.dto.QualificationTestDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class QualificationTestMapper implements RowMapper<QualificationTestDTO> {
    @Override
    public QualificationTestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        int job_opening_id = rs.getInt("job_opening_id");
        String instructions = rs.getString("instructions");
        Date given_time = rs.getTimestamp("given_time");
        return new QualificationTestDTO(job_opening_id, instructions, given_time);
    }
}
