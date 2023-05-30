package com.cs353.backend.mapper;


import com.cs353.backend.model.entities.CareerExpert;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class CareerExpertMapper implements RowMapper<CareerExpert> {
    
    @Override
    public CareerExpert mapRow(ResultSet rs, int rowNum) throws SQLException {
        int rank = rs.getInt("rank");
        int last_year_score = rs.getInt("last_year_scode");
        return new CareerExpert(rank, last_year_score);
    }
}
