package com.cs353.backend.controller;

import com.cs353.backend.model.dto.JobOpeningApplicationDTO;
import com.cs353.backend.model.dto.JobOpeningDTO;
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
    public ResponseEntity<JobOpening> createJobOpening(@Valid @RequestBody JobOpeningDTO jobOpening, @PathVariable int userId){
        JobOpening newJobOpening = jobOpeningService.createJobOpening(jobOpening, userId);
        return new ResponseEntity<>(newJobOpening, HttpStatus.OK);
    }

    @GetMapping("getAllJobOpenings")
    public List<JobOpeningDTO> getAllJobOpenings() {
        return jobOpeningService.getAllJobOpenings();
    }

    @PostMapping("getJobOpeningsByFilter")
    public List<JobOpeningDTO> getJobOpeningsByFilter(@Valid @RequestBody JobOpeningDTO jobOpeningDTO) {
        return jobOpeningService.getJobOpeningsByFilter(jobOpeningDTO);
    }

    //NOT TESTED
    @PostMapping("applyJobOpening/")
    public boolean applyJobOpening(@Valid @RequestBody JobOpeningApplicationDTO jobOpeningApplicationDTO) {
        //It is assumed that a cv is uploaded in a string format, as specified in the database.
        //It is also assumed that JobOpeningApplicationDTO.cv would be an empty string if no cv is uploaded.
        //if(jobOpeningApplicationDTO.getCv().isEmpty()) {
        //    return false;
        //}
        return jobOpeningService.applyJobOpening(jobOpeningApplicationDTO);
    }
}
