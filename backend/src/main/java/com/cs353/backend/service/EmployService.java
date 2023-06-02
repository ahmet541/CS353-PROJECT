package com.cs353.backend.service;

import com.cs353.backend.dao.ConnectionDao;
import com.cs353.backend.dao.EmployDao;
import com.cs353.backend.model.dto.EmployDTO;
import com.cs353.backend.model.dto.PostOwnerDTO;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;

/**SAFA HALLADER
 * EDUCATİON KISMINI BURDA HALLADEBİLİRZ
 */

@Service
@AllArgsConstructor
public class EmployService {

    private final EmployDao employDao;
    public void employUser(EmployDTO employDTO) {
        employDao.addEmployee(employDTO);
    }

    public void fireEmployee(int companyId, int userId) {
        employDao.deleteEmployee(companyId, userId);
    }

    public boolean isWorkingAt(int companyId, int userId) {
        return employDao.isWorkingAt(companyId, userId);
    }



}
