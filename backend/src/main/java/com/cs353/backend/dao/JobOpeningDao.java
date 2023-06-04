package com.cs353.backend.dao;

import com.cs353.backend.model.dto.JobOpeningApplicationDTO;
import com.cs353.backend.model.dto.JobOpeningDTO;
import com.cs353.backend.model.entities.JobOpening;

import java.util.List;

public interface JobOpeningDao {

    JobOpening createJobOpening(JobOpeningDTO jobOpening, int userId);

    JobOpeningDTO getJobOpeningByJobOpeningId(int jobOpeningId);

    List<JobOpeningDTO> getAllJobOpenings();

    List<JobOpeningDTO> getJobOpeningsByFilter(JobOpeningDTO jobOpeningDTO);

    void addJobField(String field, int jobOpeningId);

    //NOT TESTED
    boolean applyJobOpening(JobOpeningApplicationDTO jobOpeningApplicationDTO);

    List<JobOpening> getMyJobOpenings(int recruiterId);

    String getJobField(int jobOpeningId);
}
