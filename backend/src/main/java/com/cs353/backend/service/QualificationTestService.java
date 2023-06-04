package com.cs353.backend.service;

import com.cs353.backend.dao.QualificationTestDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QualificationTestService {
    private final QualificationTestDao qualificationTestDao;
}
