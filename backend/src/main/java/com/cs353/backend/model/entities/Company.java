package com.cs353.backend.model.entities;

import com.cs353.backend.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends User {
    private String companyName;
    private String type;
    private int economicScale;
}
