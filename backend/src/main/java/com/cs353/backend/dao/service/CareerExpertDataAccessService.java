package com.cs353.backend.dao.service;

import com.cs353.backend.dao.CareerExpertDao;
import com.cs353.backend.Enum.UserRole;
import com.cs353.backend.mapper.CareerExpertMapper;/////
import com.cs353.backend.model.entities.CareerExpert;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
public class CareerExpertDataAccessService implements CareerExpertDao{
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CareerExpert> getAllCareerExperts(){
        String sql = "SELECT * FROM career_expert";

        List<CareerExpert> careerExperts = jdbcTemplate.query(sql, new CareerExpertMapper());

        return careerExperts;
    }

    @Override
    public void createCareerExpert(CareerExpert careerExpert) {
        String sql = "INSERT INTO career_expert (rank, last_year_score) VALUES (?, ?);";
        jdbcTemplate.update(sql, careerExpert.getRank(), careerExpert.getLast_year_score());
    }
}
