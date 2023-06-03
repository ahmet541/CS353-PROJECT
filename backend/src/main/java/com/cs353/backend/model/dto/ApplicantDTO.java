package com.cs353.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private Date birthdate;
    private String address;
}
