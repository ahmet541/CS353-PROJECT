package com.cs353.backend.controller;

import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/application")
@AllArgsConstructor
public class ApplicationController {

    private ApplicationService applicationService;

    //NOT TESTED
    @PostMapping("applyJobOpening")
    public boolean applyJobOpening(@Valid @RequestBody ApplicationDTO applicationDTO) {
        //It is assumed that a cv is uploaded in a string format, as specified in the database.
        //It is also assumed that JobOpeningApplicationDTO.cv would be an empty string if no cv is uploaded.
        if(applicationDTO.getCv().isEmpty()) {
            return false;
        }
        //return
        return applicationService.applyJobOpening(applicationDTO);
    }
}
