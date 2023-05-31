package com.cs353.backend.dao.service;

import com.cs353.backend.dao.JobOpeningDao;
import com.cs353.backend.model.entities.JobOpening;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JobOpeningDataAccessServer implements JobOpeningDao{
    @Override
    public JobOpening createJobOpening(JobOpening jobOpening, int userId) {
        return null;
    }
}
