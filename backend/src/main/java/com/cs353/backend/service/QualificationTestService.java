package com.cs353.backend.service;

import com.cs353.backend.dao.QualificationTestDao;
import com.cs353.backend.dao.service.QualificationTestDataAccessService;
import com.cs353.backend.model.dto.QualificationTestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QualificationTestService {
    private final QualificationTestDataAccessService qualificationTestDataAccessService;

    public boolean createQualificationTest(QualificationTestDTO qualificationTestDTO) {
        return qualificationTestDataAccessService.createQualificationTest(qualificationTestDTO);
    }
}
