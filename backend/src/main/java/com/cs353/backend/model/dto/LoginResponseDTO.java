package com.cs353.backend.model.dto;

import com.cs353.backend.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponseDTO {
    private UserRole role;
    private int userId;
}
