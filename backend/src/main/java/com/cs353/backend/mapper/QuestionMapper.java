package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        int question_id = rs.getInt("question_id");
        int th_question = rs.getInt("th_question");
        String content = rs.getString("content");
        String answer = rs.getString("answer");
        return new Question( question_id, th_question ,content, answer);
    }
}
