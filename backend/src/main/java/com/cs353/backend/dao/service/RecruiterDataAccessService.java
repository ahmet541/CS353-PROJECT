package com.cs353.backend.dao.service;

import com.cs353.backend.dao.RecruiterDao;
import com.cs353.backend.mapper.RecruiterMapper;
import com.cs353.backend.mapper.RegularUserMapper;
import com.cs353.backend.model.entities.Recruiter;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class RecruiterDataAccessService implements RecruiterDao{

    private JdbcTemplate jdbcTemplate;

    @Override
    public Recruiter getRecruiterById(int id) {
        String sql = """
                     SELECT R.* 
                     FROM recruiter R
                     JOIN "User" U ON U.id = R.id
                     WHERE R.id = ?
                     """;
        return jdbcTemplate.queryForObject(sql, new RecruiterMapper(), id);
    }

    @Override
    public int addRecruiter(int id) {
        String sql = "INSERT INTO recruiter (id, recruiting_start_date) VALUES (?, CURRENT_TIMESTAMP);";
        jdbcTemplate.update(sql, id);
        return id;
    }


}
