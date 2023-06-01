package com.cs353.backend.model.dto;

import com.cs353.backend.Enum.UserRole;
import com.cs353.backend.model.entities.Account;
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
public class UserProfileDTO {
    private int userId;
    private String fullName;
    private String profileDescription;
    private String userAvatar;
    private String phoneNumber;
    private String gender;
    private Date birthdate;
    private String address;
    private String companyType;
    private int economicScale;
    private List<String> Certificate_Skills = new ArrayList<>();
    private List<String> workExperience = new ArrayList<>();
    private Date recruiting_startDate;
    private int rank;
    private double last_year_score;
    private List<UserRole> roles = new ArrayList<>();
    private List<PostOwnerDTO> followers = new ArrayList<>();
    private List<PostOwnerDTO> connections = new ArrayList<>();
    private List<PostOwnerDTO> employees = new ArrayList<>();
}
