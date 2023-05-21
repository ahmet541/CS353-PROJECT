package com.cs353.backend.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerExpert extends RegularUser{
    private int rank;
    private int last_year_score;
}
