package com.cs353.backend.dao.service;

import com.cs353.backend.dao.QuestionDao;
import com.cs353.backend.mapper.QuestionDTOMapper;
import com.cs353.backend.mapper.QuestionMapper;
import com.cs353.backend.model.dto.QuestionDTO;
import com.cs353.backend.model.dto.QuestionUpdateDTO;
import com.cs353.backend.model.entities.Question;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@AllArgsConstructor
public class QuestionDataAccessService implements QuestionDao {

    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Question> getQuestions(int qualification_test_id) {
        String sql = """
                SELECT Q.*
                FROM question Q
                WHERE Q.qualification_test_id = ?
                """;

        return jdbcTemplate.query(sql, new QuestionMapper(), qualification_test_id);
    }

    /*NOT TESTED YET...*/
    @Override
    public boolean createQuestions(List<QuestionDTO> questions, int qualification_test_id) {
        if(questions.isEmpty()) {
            return false;
        }
        StringBuilder sql = new StringBuilder("INSERT INTO questions (qualification_test_id, th_question, content, answer) VALUES ");
        for (QuestionDTO ignored : questions) {
            sql.append("( ?, ?, ?, ?), ");
        }

        return jdbcTemplate.update(sql.toString(), questions) > 0;
    }



    @Override
    public boolean updateQuestion(QuestionUpdateDTO questionUpdateDTO, int question_id) {
        String sql = """
                UPDATE question
                SET content = ? , answer = ?
                WHERE question_id = ?
                """;
        return jdbcTemplate.update(sql,
                questionUpdateDTO.getContent(),
                questionUpdateDTO.getAnswer(),
                question_id) > 0;
    }
}
