package com.cs353.backend.model.dto;

import com.cs353.backend.model.entities.JobOpening;
import com.cs353.backend.model.entities.RegularUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningApplicantsDTO {
    private JobOpening jobOpening;
    private List<ApplicantDTO> applicants;
}
