package com.cs353.backend.dao.service;

import com.cs353.backend.dao.QuestionDao;
import com.cs353.backend.model.dto.QuestionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class QuestionDataAccessService implements QuestionDao {
    @Override
    public List<QuestionDTO> getQuestions(int qualification_test_id) {
        return null; //TO BE IMPLEMENTED
    }
}
