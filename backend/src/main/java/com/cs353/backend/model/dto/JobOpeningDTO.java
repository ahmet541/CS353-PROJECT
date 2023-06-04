package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningDTO {
    private int jobOpeningId;
    private String employmentStatus;
    private String explanation;
    private LocalDateTime dueDate;
    private String rolePro;
    private String location;
    private String workType;
    private String field;
    private int companyId;
    private String companyName;
    private LocalDateTime minDueDate;
    private LocalDateTime maxDueDate;

    public String getEmploymentStatus() {
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