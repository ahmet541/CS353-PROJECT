package com.cs353.backend.controller;

import com.cs353.backend.model.entities.CareerExpert;
import com.cs353.backend.service.CareerExpertService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career-expert")
@AllArgsConstructor
public class CareerExpertController {
    private CareerExpertService careerExpertService;

    @GetMapping("getAll")
    public ResponseEntity<List<CareerExpert>> getAllCareerExperts(){
        List<CareerExpert> careerExperts = careerExpertService.getAllCareerExperts();
        return new ResponseEntity<>(careerExperts, HttpStatus.OK); 
    }


    @PostMapping
    public ResponseEntity<String> addCareerExpert(@RequestBody CareerExpert careerExpert) {
        careerExpertService.createCareerExpert(careerExpert);
        return new ResponseEntity<>("Career Expert added successfully", HttpStatus.CREATED);
    }
    
}
