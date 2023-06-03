package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {

    Integer user_id;
    Integer job_opening_id;
    Date applicationDate;
    Integer application_status;
    String experience;
    String  skills;
    Integer education_lvl;
    String  cv;



}
