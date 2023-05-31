package com.cs353.backend.dao;

import com.cs353.backend.model.entities.JobOpening;

public interface JobOpeningDao {

    JobOpening createJobOpening(JobOpening jobOpening, int userId);
}
