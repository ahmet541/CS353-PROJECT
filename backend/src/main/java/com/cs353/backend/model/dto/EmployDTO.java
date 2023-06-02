package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployDTO {

    int company_id;
    int  regular_user_id;
    int recruiter_id;
    int emp_role;
    Date emp_start_date;
    Date emp_end_date;

}
