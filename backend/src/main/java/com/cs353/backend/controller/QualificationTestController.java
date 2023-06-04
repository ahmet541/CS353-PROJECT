package com.cs353.backend.controller;

import com.cs353.backend.dao.QualificationTestDao;
import com.cs353.backend.model.dto.QualificationTestDTO;
import com.cs353.backend.service.QualificationTestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/qualificationTest")
@AllArgsConstructor
public class QualificationTestController {
    private QualificationTestService qualificationTestService;

    @PostMapping("createQualificationTest")
    public boolean createQualificationTest(@Valid @RequestBody QualificationTestDTO qualificationTestDTO) {
        return qualificationTestService.createQualificationTest(qualificationTestDTO);
    }
}