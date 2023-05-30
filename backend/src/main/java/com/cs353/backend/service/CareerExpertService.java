package com.cs353.backend.service;

import com.cs353.backend.dao.CareerExpertDao;
import com.cs353.backend.model.entities.CareerExpert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CareerExpertService {
    private final CareerExpertDao careerExpertDao;

    public List<CareerExpert> getAllCareerExperts() {
        return careerExpertDao.getAllCareerExperts();
    }

    public void createCareerExpert(CareerExpert careerExpert) {
        careerExpertDao.createCareerExpert(careerExpert);
    }

    public CareerExpert getCareerExpert(int userId) {
        return null; // TODO
    }
}