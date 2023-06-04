package com.cs353.backend.service;

import com.cs353.backend.dao.service.QualificationTestDataAccessService;
import com.cs353.backend.dao.service.QuestionDataAccessService;
import com.cs353.backend.model.dto.QuestionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionDataAccessService questionDataAccessService;

    public List<QuestionDTO> getQuestions(int qualification_test_id) {
        return questionDataAccessService.getQuestions(qualification_test_id);
    }

    public boolean createQuestions(List<QuestionDTO> questions, int qualification_test_id) {
        return questionDataAccessService.createQuestions(questions, qualification_test_id);
    }
}
