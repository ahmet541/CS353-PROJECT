package com.cs353.backend.service;

import com.cs353.backend.dao.RecruiterDao;
import com.cs353.backend.model.entities.Recruiter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecruiterService {
    private final RecruiterDao recruiterDao;
    public Recruiter getRecruiter(int userId) {
        return recruiterDao.getRecruiterById(userId);
    }

    public int addRecruiter(int userid){
        return recruiterDao.addRecruiter(userid);
    }

    public boolean recruiterExist(int userid){
        return recruiterDao.recruiterExist(userid);
    }
}
