package com.cs353.backend.mapper;

import com.cs353.backend.model.dto.QuestionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDTOMapper implements RowMapper<QuestionDTO> {

    @Override
    public QuestionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        //String content = rs.getString("")
        int th_question = rs.getInt("th_question");
        String content = rs.getString("content");
        String answer = rs.getString("answer");
        return new QuestionDTO(th_question ,content, answer);
    }
}
