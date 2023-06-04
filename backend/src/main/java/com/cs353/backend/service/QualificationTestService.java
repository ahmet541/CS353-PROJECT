package com.cs353.backend.service;

import com.cs353.backend.dao.QualificationTestDao;
import com.cs353.backend.dao.service.QualificationTestDataAccessService;
import com.cs353.backend.model.dto.QualificationTestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QualificationTestService {
    private final QualificationTestDataAccessService qualificationTestDataAccessService;

    public boolean createQualificationTest(QualificationTestDTO qualificationTestDTO) {
        return qualificationTestDataAccessService.createQualificationTest(qualificationTestDTO);
    }

    public List<QualificationTestDTO> getQualificationTests(int job_opening_id) {
        return qualificationTestDataAccessService.getQualificationTests(job_opening_id);
    }
}
