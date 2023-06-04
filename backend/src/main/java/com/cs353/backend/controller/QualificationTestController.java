package com.cs353.backend.controller;

import com.cs353.backend.service.QualificationTestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qualificationTest")
@AllArgsConstructor
public class QualificationTestController {
    private QualificationTestService qualificationTestService;
}
