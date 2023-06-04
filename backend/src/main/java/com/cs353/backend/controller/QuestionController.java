package com.cs353.backend.controller;

import com.cs353.backend.model.dto.QuestionDTO;
import com.cs353.backend.service.QualificationTestService;
import com.cs353.backend.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @PostMapping("/createQuestions/{qualification_test_id}")
    public boolean createQuestions(@Valid @RequestBody List<QuestionDTO> questions, int qualification_test_id) {
        return questionService.createQuestions(questions, qualification_test_id);
    }
    @GetMapping("/getQuestions/{qualification_test_id}")
    public List<QuestionDTO> getQuestions(@PathVariable int qualification_test_id) {
        return questionService.getQuestions(qualification_test_id);
    }
}