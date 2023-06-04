package com.cs353.backend.dao;

import com.cs353.backend.model.dto.QuestionDTO;
import com.cs353.backend.model.dto.QuestionUpdateDTO;
import com.cs353.backend.model.entities.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions(int qualification_test_id);

    boolean createQuestions(List<QuestionDTO> questions, int qualification_test_id);

    boolean updateQuestion(QuestionUpdateDTO questionUpdateDTO, int question_id);
}
