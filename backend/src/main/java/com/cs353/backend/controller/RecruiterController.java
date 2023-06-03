package com.cs353.backend.controller;

import com.cs353.backend.dao.ApplicationDao;
import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.model.entities.JobOpening;
import com.cs353.backend.service.JobOpeningService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
@AllArgsConstructor
public class RecruiterController {

    private JobOpeningService jobOpeningService;


    @GetMapping("myJobOpenings/{recruiterId}")
    public List<JobOpening> getMyJobOpenings( @PathVariable int recruiterId) {
        return jobOpeningService.getMyJobOpenings(recruiterId);
    }

    @GetMapping("applications/{jobOpeningId]")
    public List<ApplicationDTO> getApplications(@PathVariable int jobOpeningId) {
        return jobOpeningService.getApplicationsOfJobOpening(jobOpeningId);
    }




}
