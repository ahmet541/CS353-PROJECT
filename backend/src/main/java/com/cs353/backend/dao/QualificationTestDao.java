package com.cs353.backend.dao;

import com.cs353.backend.model.dto.QualificationTestDTO;

public interface QualificationTestDao {

    boolean createQualificationTest(QualificationTestDTO qualificationTestDTO);
}
