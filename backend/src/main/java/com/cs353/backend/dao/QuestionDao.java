package com.cs353.backend.dao;

import com.cs353.backend.model.dto.QuestionDTO;

import java.util.List;

public interface QuestionDao {
    List<QuestionDTO> getQuestions(int qualification_test_id);

    boolean createQuestions(List<QuestionDTO> questions, int qualification_test_id);
}
