package com.cs353.backend.controller;

import com.cs353.backend.model.entities.JobOpening;
import com.cs353.backend.service.JobOpeningService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/jobopening")
@AllArgsConstructor
public class JobOpeningController {

    private JobOpeningService jobOpeningService;
    @PostMapping("{userId}")
    public ResponseEntity<JobOpening> createJobOpening(@Valid @RequestBody JobOpening jobOpening, int userId){
        JobOpening newJobOpening = jobOpeningService.createJobOpening(jobOpening, userId);
        return new ResponseEntity<>(newJobOpening, HttpStatus.OK);
    }
}
