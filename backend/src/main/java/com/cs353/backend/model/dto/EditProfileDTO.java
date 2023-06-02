package com.cs353.backend.model.dto;

import com.cs353.backend.Enum.UserRole;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Certificate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileDTO {
    private int userId;
    private String companyName;
    private String firstName;
    private String lastName;
    private String profileDescription;
    private String userAvatar;
    private String phoneNumber;
    private String gender;
    private Date birthdate;
    private String address;
    private String companyType;
    private int economicScale;
    private List<Certificate> Certificate_Skills = new ArrayList<>();
    private List<UserRole> roles = new ArrayList<>();
}
