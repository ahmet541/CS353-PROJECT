package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployDTO {

    Integer company_id;
    Integer  regular_user_id;
    Integer recruiter_id;
    String emp_role;
    Date emp_start_date;
    Date emp_end_date;

}
