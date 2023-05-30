package com.cs353.backend.model.entities;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationTest {
    private int     test_id;
    private int     company_id;
    private int     job_opening_id;
    private String  instructions;
    private Time    given_time; //This may be changed.
}
