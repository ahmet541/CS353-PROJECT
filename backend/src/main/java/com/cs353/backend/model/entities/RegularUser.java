package com.cs353.backend.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegularUser extends User {
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private Date birthdate;
    private String address;
}
