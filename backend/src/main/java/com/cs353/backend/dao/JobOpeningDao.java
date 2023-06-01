package com.cs353.backend.dao;

import com.cs353.backend.model.entities.JobOpening;

import java.util.List;

public interface JobOpeningDao {

    JobOpening createJobOpening(JobOpening jobOpening, int userId);
    List<JobOpening> getAllJobOpenings();
}
