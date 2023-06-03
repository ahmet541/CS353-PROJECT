package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningApplicationDTO {
    /*
    * TO BE IMPLEMENTED BY ERKIN AYDIN*/
    private int userId;
    private int jobOpeningId;
    private String applicationStatus;
    private String experience;
    private String skills;
    private String educationLvl;
    private String cv;

}
