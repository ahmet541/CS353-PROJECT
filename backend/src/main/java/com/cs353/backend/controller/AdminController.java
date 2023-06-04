package com.cs353.backend.controller;

import com.cs353.backend.dao.service.AdminDataAccessService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminDataAccessService adminService;

    @GetMapping("/employee-recruiter-counts")
    public List<Map<String, Object>> getRecruiterEmployeeCounts() {
        return adminService.getRecruiterEmployeeCounts();
    }

    @GetMapping("/employee-recruiter-stats")
    public List<Map<String, Object>> getNumberOfEmployeesAndRecruiters() {
        return adminService.numberOfEmployeesAndRecruiters();
    }
}
