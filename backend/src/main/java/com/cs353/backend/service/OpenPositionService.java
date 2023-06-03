package com.cs353.backend.service;


import com.cs353.backend.dao.OpenPositionDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpenPositionService {
    private final OpenPositionDao openPositionDao;

    public void addOpenPosition(int companyId, int recruiterId, int jobOpeningId){
        openPositionDao.addOpenPosition(companyId, recruiterId, jobOpeningId);
    }

    public int getCompanyId(int jobOpeningId){
        return openPositionDao.getCompanyId(jobOpeningId);
    }

}
