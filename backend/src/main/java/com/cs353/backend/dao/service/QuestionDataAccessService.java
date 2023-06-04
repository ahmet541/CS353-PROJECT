package com.cs353.backend.dao.service;

import com.cs353.backend.dao.QuestionDao;
import com.cs353.backend.mapper.QuestionDTOMapper;
import com.cs353.backend.model.dto.QuestionDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class QuestionDataAccessService implements QuestionDao {

    private JdbcTemplate jdbcTemplate;
    @Override
    public List<QuestionDTO> getQuestions(int qualification_test_id) {
        String sql = """
                SELECT Q.*
                FROM question Q
                WHERE Q.qualification_test_id = ?
                """;

        return jdbcTemplate.query(sql, new QuestionDTOMapper(), qualification_test_id);
    }
}
