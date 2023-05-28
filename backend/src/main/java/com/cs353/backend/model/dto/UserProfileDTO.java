package com.cs353.backend.model.dto;

import com.cs353.backend.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    private int userId;
    private String fullName;
    private String profileDescription;
    private String userAvatar;
    private String phoneNumber;
    private String gender;
    private Date birthdate;
    private String address;
    private String companyName;
    private String companyType;
    private int economicScale;
    private List<String> Certificate_Skills;
    private List<String> workExperience;
    private Date recruiting_startDate;
    private int rank;
    private double last_year_score;
    private List<UserRole> roles;
    private int followers;
    private int numberOfConnections;
    private int numberOfFollowers;
    private int numberOfEmployees;

}
