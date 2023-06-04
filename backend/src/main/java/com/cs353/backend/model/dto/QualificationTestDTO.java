package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationTestDTO {
    private int     job_opening_id;
    private String  instructions;
    private Time given_time;
}
