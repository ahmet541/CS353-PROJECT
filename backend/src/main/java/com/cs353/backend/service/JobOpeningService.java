package com.cs353.backend.service;

import com.cs353.backend.dao.JobOpeningDao;
import com.cs353.backend.model.entities.JobOpening;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobOpeningService {
    private final JobOpeningDao jobOpeningDao;

    public JobOpening createJobOpening(JobOpening jobOpening, int userId) {
        return jobOpeningDao.createJobOpening(jobOpening, userId);
    }
}
