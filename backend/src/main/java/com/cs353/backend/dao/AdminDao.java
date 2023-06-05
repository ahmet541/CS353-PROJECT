package com.cs353.backend.dao;

import com.cs353.backend.model.entities.User;

import java.util.List;
import java.util.Map;

public interface AdminDao {
    List<Map<String, Object>> numberOfEmployeesAndRecruiters();

    List<Map<String, Object>> getRecruiterEmployeeCounts();

    void createAdmin(User admin, String name);
}
