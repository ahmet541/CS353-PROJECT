package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningApplicationDTO {
    /*
    * TO BE IMPLEMENTED BY ERKIN AYDIN*/
    private int userId;
    private int jobOpeningId;
    private Date applicationDate;
    private int applicationStatus;
    private String experience;
    private String skills;
    private int educationLvl;
    private String cv;
}
