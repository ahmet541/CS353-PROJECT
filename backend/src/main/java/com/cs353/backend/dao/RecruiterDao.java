package com.cs353.backend.dao;

import com.cs353.backend.model.entities.Recruiter;

public interface RecruiterDao {
    Recruiter getRecruiterById(int id);
    int addRecruiter(int id);

    boolean recruiterExist(int id);
}
