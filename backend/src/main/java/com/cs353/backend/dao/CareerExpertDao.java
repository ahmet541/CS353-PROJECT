package com.cs353.backend.dao;


import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import com.cs353.backend.model.entities.CareerExpert;
import java.util.List;
public interface CareerExpertDao {
    List<CareerExpert> getAllCareerExperts();

    void createCareerExpert(CareerExpert careerExpert);
}
