package com.cs353.backend.dao.service;

import com.cs353.backend.dao.QualificationTestDao;
import com.cs353.backend.model.dto.QualificationTestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class QualificationTestDataAccessService implements QualificationTestDao {
    @Override
    public boolean createQualificationTest(QualificationTestDTO qualificationTestDTO, int jobOpeningId) {

        /*TO BE IMPLEMENTED*/
        return true;//for now.
    }
}
