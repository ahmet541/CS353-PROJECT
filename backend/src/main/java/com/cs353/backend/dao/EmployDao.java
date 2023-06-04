package com.cs353.backend.dao;

import com.cs353.backend.model.dto.EmployDTO;
import com.cs353.backend.model.dto.PostOwnerDTO;
import com.cs353.backend.model.entities.Company;

import java.util.List;

public interface EmployDao {
    void addEmployee(EmployDTO employDTO);
    void deleteEmployee(int companyId, int employeeId);

    boolean isWorkingAt(int companyId, int employeeId);

    void deleteEmployeePermanent(int companyId, Integer userId);

    int numberOfEmployees(int companyId);

    List<PostOwnerDTO> getAllEmployees(int userId);
}
