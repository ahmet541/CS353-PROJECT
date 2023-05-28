package com.cs353.backend.service;

import com.cs353.backend.Enum.UserRole;
import com.cs353.backend.model.dto.UserProfileDTO;
import com.cs353.backend.model.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final RegularUserService regularUserService;
    private final CompanyService companyService;
    private final CareerExpertService careerExpertService;
    private final RecruiterService recruiterService;

    public UserProfileDTO getProfileInfo(int userId){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserId(userId);
        List<UserRole> roles = new ArrayList<>();

        try {
            RegularUser regularUser = regularUserService.getRegularUser(userId);
            userProfileDTO.setProfileDescription(regularUser.getProfileDescription());
            userProfileDTO.setBirthdate(regularUser.getBirthdate());
            userProfileDTO.setAddress(regularUser.getAddress());
            userProfileDTO.setGender(regularUser.getGender());
            userProfileDTO.setFullName(regularUser.getFirstName() + " " + regularUser.getLastName());
            userProfileDTO.setPhoneNumber(regularUser.getPhoneNumber());
            roles.add(UserRole.REGULAR_USER);
        }
        catch (Exception e){
            // this person may not be regularUser, it is okey
            // instead of this we could write isRegularUser() method etc. but reqires extra work
        }

        try {
            Company company = companyService.getCompany(userId);
            if(company == null){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "");
            }
            userProfileDTO.setProfileDescription(company.getProfileDescription());
            userProfileDTO.setFullName(company.getCompanyName());
            userProfileDTO.setCompanyType(company.getType());
            userProfileDTO.setEconomicScale(company.getEconomicScale());
            roles.add(UserRole.COMPANY);
        }
        catch (Exception e){
            // This person may not be a company, it's okay
            // Instead of this, you could write an isCompany() method, etc., but it requires extra work
        }

        try {
            CareerExpert careerExpert = careerExpertService.getCareerExpert(userId);
            if(careerExpert == null){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "");
            }
            userProfileDTO.setRank(careerExpert.getRank());
            userProfileDTO.setLast_year_score(careerExpert.getLast_year_score());
            roles.add(UserRole.CAREER_EXPERT);
        }
        catch (Exception e){
            // This person may not be a career expert, it's okay
            // Instead of this, you could write an isCareerExpert() method, etc., but it requires extra work
        }

        try {
            Recruiter recruiter = recruiterService.getRecruiter(userId);
            if(recruiter == null){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "");
            }
            userProfileDTO.setRecruiting_startDate(recruiter.getRecruting_start_date());
            roles.add(UserRole.RECRUITER);
        }
        catch (Exception e){
            // This person may not be a recruiter, it's okay
            // Instead of this, you could write an isRecruiter() method, etc., but it requires extra work
        }
        if(roles.size() == 0){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This user does not exist. Or user type is restricted.");
        }
        userProfileDTO.setRoles(roles);
        return userProfileDTO;
    }

}
