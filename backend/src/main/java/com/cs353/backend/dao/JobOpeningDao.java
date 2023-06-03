package com.cs353.backend.dao;

import com.cs353.backend.model.dto.JobOpeningApplicantsDTO;
import com.cs353.backend.model.dto.JobOpeningApplicationDTO;
import com.cs353.backend.model.dto.JobOpeningDTO;
import com.cs353.backend.model.entities.JobOpening;

import java.util.List;

public interface JobOpeningDao {

    JobOpening createJobOpening(JobOpeningDTO jobOpening, int userId);
    List<JobOpening> getAllJobOpenings();

    List<JobOpening> getJobOpeningsByFilter(JobOpeningDTO jobOpeningDTO);

    void addJobField(String field, int jobOpeningId);

    //NOT TESTED
    boolean applyJobOpening(JobOpeningApplicationDTO jobOpeningApplicationDTO);

    List<JobOpeningApplicantsDTO> getMyJobOpenings(int recruiterId);
}
