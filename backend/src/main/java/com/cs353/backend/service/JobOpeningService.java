package com.cs353.backend.service;

import com.cs353.backend.dao.ApplicationDao;
import com.cs353.backend.dao.JobOpeningDao;
import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.model.dto.JobOpeningApplicantsDTO;
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

    private  final ApplicationDao applicationDao;
    public JobOpening createJobOpening(JobOpeningDTO jobOpening, int userId) {
        boolean recruiterExist = recruiterService.recruiterExist(userId);
        int recruitersCompanyId = companyService.isRecruiterVerified(userId);
        if (recruitersCompanyId == -1){
            System.out.println("Recruiter has no company!!!");
            return null;
        }
        JobOpening newJobOpening = jobOpeningDao.createJobOpening(jobOpening, userId);
        openPositionService.addOpenPosition(recruitersCompanyId, userId, newJobOpening.getJobOpeningID());
        jobOpeningDao.addJobField(jobOpening.getField(), newJobOpening.getJobOpeningID());
        return newJobOpening;
    }

    public List<JobOpeningDTO> getAllJobOpenings() {
        List<JobOpeningDTO> jobOpeningDTOList = jobOpeningDao.getAllJobOpenings();
        for (int i = 0; i < jobOpeningDTOList.size(); i++){
            int companyId = openPositionService.getCompanyId(jobOpeningDTOList.get(i).getJobOpeningId());
            String field = jobOpeningDao.getJobField(jobOpeningDTOList.get(i).getJobOpeningId());
            jobOpeningDTOList.get(i).setCompanyId(companyId);
            jobOpeningDTOList.get(i).setField(field);
        }
        return jobOpeningDTOList;
    }

    public List<JobOpeningDTO> getJobOpeningsByFilter(JobOpeningDTO jobOpeningDTO) {
        return jobOpeningDao.getJobOpeningsByFilter(jobOpeningDTO);
    }



    public boolean applyJobOpening(JobOpeningApplicationDTO jobOpeningApplicationDTO) {
        return jobOpeningDao.applyJobOpening(jobOpeningApplicationDTO);
    }

    public ApplicationDTO getApplication(int userId, int jobOpeningId){
        return applicationDao.getApplication(userId, jobOpeningId);
    }

    public List<ApplicationDTO> getApplicationsOfJobOpening(int jobOpeningId){
        return applicationDao.getApplicationsOfJobOpening(jobOpeningId);
    }

    public List<JobOpeningApplicantsDTO> getMyJobOpenings(int recruiterId) {
        return jobOpeningDao.getMyJobOpenings(recruiterId);
    }

}
