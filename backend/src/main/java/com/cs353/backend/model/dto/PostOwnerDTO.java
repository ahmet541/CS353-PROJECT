package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOwnerDTO {
    private int userId;
    private String fullName;
    private String avatar;
}
