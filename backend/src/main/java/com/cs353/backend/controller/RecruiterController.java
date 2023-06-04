package com.cs353.backend.controller;

import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.model.dto.JobOpeningApplicantsDTO;
import com.cs353.backend.model.entities.JobOpening;
import com.cs353.backend.service.ApplicationService;
import com.cs353.backend.service.JobOpeningService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
@AllArgsConstructor
public class RecruiterController {

    private JobOpeningService jobOpeningService;
    private ApplicationService applicationService;


    @GetMapping("myJobOpenings/{recruiterId}")
    public List<JobOpeningApplicantsDTO> getMyJobOpenings(@PathVariable int recruiterId) {
        return jobOpeningService.getMyJobOpenings(recruiterId);
    }

    @GetMapping("applications/{jobOpeningId}")
    public List<ApplicationDTO> getApplications(@PathVariable int jobOpeningId) {
        return jobOpeningService.getApplicationsOfJobOpening(jobOpeningId);
    }

    @PostMapping("hire/{jobOpeningId}/{userId}")
    public void setApplication(@PathVariable int jobOpeningId, @PathVariable int userId) {
        applicationService.hire(jobOpeningId, userId);
    }


}
