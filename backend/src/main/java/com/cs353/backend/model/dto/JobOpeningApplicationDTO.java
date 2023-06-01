package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningApplicationDTO {
    /*
    * TO BE IMPLEMENTED BY ERKIN AYDIN*/
    private LocalDateTime applicationDate;
    private String applicationStatus;
    private String experience;
    private String skills;
    private String educationLvl;
    private String cv;

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getExperience() {
        return experience;
    }

    public String getSkills() {
        return skills;
    }

    public String getEducationLvl() {
        return educationLvl;
    }

    public String getCv() {
        return cv;
    }
}
