package com.cs353.backend.service;

import com.cs353.backend.dao.JobOpeningDao;
import com.cs353.backend.model.dto.JobOpeningApplicationDTO;
import com.cs353.backend.model.dto.JobOpeningDTO;
import com.cs353.backend.model.entities.JobOpening;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobOpeningService {
    private final JobOpeningDao jobOpeningDao;

    public JobOpening createJobOpening(JobOpening jobOpening, int userId) {
        return jobOpeningDao.createJobOpening(jobOpening, userId);
    }

    public List<JobOpening> getAllJobOpenings() {
        return jobOpeningDao.getAllJobOpenings();
    }

    public List<JobOpening> getJobOpeningsByFilter(JobOpeningDTO jobOpeningDTO) {
        return jobOpeningDao.getJobOpeningsByFilter(jobOpeningDTO);
    }

    //NOT TESTED
    public boolean applyJobOpening(JobOpeningApplicationDTO jobOpeningApplicationDTO) {
        return jobOpeningDao.applyJobOpening(jobOpeningApplicationDTO);
    }
}
