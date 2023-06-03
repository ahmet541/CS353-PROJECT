package com.cs353.backend.dao;

import com.cs353.backend.mapper.ApplicationDTOMapper;
import com.cs353.backend.model.dto.ApplicationDTO;

import java.util.List;

public interface ApplicationDao {

    List<ApplicationDTO> getMyApplications(int userId);

    ApplicationDTO getApplication(int userId, int job_opening_id);

    List<ApplicationDTO> getApplicationsOfJobOpening(int jobOpeningId);

    boolean applyJobOpening(ApplicationDTO applicationDTO);
}
