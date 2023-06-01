package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningDTO {
    private Integer employmentStatus;
    private String explanation;
    private LocalDateTime dueDate;
    private String rolePro;
    private String location;
    private String workType;

    public Integer getEmploymentStatus() {
        return employmentStatus;
    }

    public String getExplanation() {
        return explanation;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getRolePro() {
        return rolePro;
    }

    public String getLocation() {
        return location;
    }

    public String getWorkType() {
        return workType;
    }
}