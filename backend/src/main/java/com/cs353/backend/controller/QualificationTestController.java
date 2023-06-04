package com.cs353.backend.controller;

import com.cs353.backend.dao.QualificationTestDao;
import com.cs353.backend.model.dto.QualificationTestDTO;
import com.cs353.backend.service.QualificationTestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/qualificationTest")
@AllArgsConstructor
public class QualificationTestController {
    private QualificationTestService qualificationTestService;

    @PostMapping("createQualificationTest")
    public boolean createQualificationTest(@Valid @RequestBody QualificationTestDTO qualificationTestDTO) {
        return qualificationTestService.createQualificationTest(qualificationTestDTO);
    }

    @GetMapping("getQualificationTests/{job_opening_id}")
    public List<QualificationTestDTO> getQualificationTests(@PathVariable int job_opening_id) {
        return qualificationTestService.getQualificationTests(job_opening_id);

    }
}