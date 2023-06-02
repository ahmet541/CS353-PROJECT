package com.cs353.backend.service;

import com.cs353.backend.dao.JobOpeningDao;
import com.cs353.backend.model.dto.JobOpeningApplicationDTO;
import com.cs353.backend.model.dto.JobOpeningDTO;
import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.JobOpening;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobOpeningService {
    private final JobOpeningDao jobOpeningDao;
    private final RecruiterService recruiterService;
    private final CompanyService companyService;
    private final OpenPositionService openPositionService;
    public JobOpening createJobOpening(JobOpening jobOpening, int userId) {
        boolean recruiterExist = recruiterService.recruiterExist(userId);
        int recruitersCompanyId = companyService.isRecruiterVerified(userId);
        if (recruitersCompanyId == -1){
            System.out.println("Recruiter has no company!!!");
            return null;
        }
        JobOpening newJobOpening = jobOpeningDao.createJobOpening(jobOpening, userId);
        openPositionService.addOpenPosition(recruitersCompanyId, userId, newJobOpening.getJobOpeningID());
        return newJobOpening;
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
