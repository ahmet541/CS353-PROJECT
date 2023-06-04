package com.cs353.backend.dao;

import com.cs353.backend.model.dto.QualificationTestDTO;

import java.util.List;

public interface QualificationTestDao {

    boolean createQualificationTest(QualificationTestDTO qualificationTestDTO);
    List<QualificationTestDTO> getQualificationTests(int jobOpeningId);
}
