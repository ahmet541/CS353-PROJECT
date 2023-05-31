package com.cs353.backend.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpening {
    private int jobOpeningID;
    private int employmentStatus;
    private String explanation;
    private LocalDateTime dueDate;
    private String rolePro;
    private String location;
    private String workType;

}
