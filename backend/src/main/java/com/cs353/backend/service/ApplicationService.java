package com.cs353.backend.service;

import com.cs353.backend.dao.ApplicationDao;
import com.cs353.backend.model.dto.ApplicationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationDao applicationDao;

    //NOT TESTED
    public boolean applyJobOpening(ApplicationDTO applicationDTO) {
        return applicationDao.applyJobOpening(applicationDTO);
    }

    public void hire(int jobOpeningId, int userId) {
        applicationDao.hire(jobOpeningId, userId);
    }
}
