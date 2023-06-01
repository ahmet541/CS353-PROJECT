package com.cs353.backend.controller;

import com.cs353.backend.model.entities.JobOpening;
import com.cs353.backend.service.JobOpeningService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("getAllJobOpenings")
    public List<JobOpening> getAllJobOpenings() {
        return jobOpeningService.getAllJobOpenings();
    }
}
