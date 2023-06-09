package com.cs353.backend.service;

import com.cs353.backend.Enum.UserRole;
import com.cs353.backend.model.dto.EditProfileDTO;
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
    private final ConnectionService connectionService;
    private final FollowService followService;

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
            //userProfileDTO.setCertificate_Skills(regularUserService.getCertificates(userId));
            System.out.println("40");
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
            userProfileDTO.setNumberOfEmployees(companyService.getNumberOfEmployees(userId));
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

        userProfileDTO.setConnections(connectionService.getAllConnections(userId));
        userProfileDTO.setFollowers(followService.getAllFollowers(userId));

        if (roles.contains(UserRole.COMPANY)){
            userProfileDTO.setEmployees(companyService.getAllEmployees(userId));
        }
        return userProfileDTO;
    }

    public EditProfileDTO getEditForm(int userId) {
        EditProfileDTO editProfileDTO = new EditProfileDTO();

        editProfileDTO.setUserId(userId);
        List<UserRole> roles = new ArrayList<>();

        try {
            RegularUser regularUser = regularUserService.getRegularUser(userId);
            roles.add(UserRole.REGULAR_USER);
            editProfileDTO.setProfileDescription(regularUser.getProfileDescription());
            editProfileDTO.setBirthdate(regularUser.getBirthdate());
            editProfileDTO.setAddress(regularUser.getAddress());
            editProfileDTO.setGender(regularUser.getGender());
            editProfileDTO.setFirstName(regularUser.getFirstName());
            editProfileDTO.setLastName(regularUser.getLastName());
            editProfileDTO.setPhoneNumber(regularUser.getPhoneNumber());
            editProfileDTO.setCertificate_Skills(regularUserService.getCertificates(userId));
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
            roles.add(UserRole.COMPANY);
            editProfileDTO.setProfileDescription(company.getProfileDescription());
            editProfileDTO.setCompanyName(company.getCompanyName());
            editProfileDTO.setCompanyType(company.getType());
            editProfileDTO.setEconomicScale(company.getEconomicScale());
        }
        catch (Exception e){
            // This person may not be a company, it's okay
            // Instead of this, you could write an isCompany() method, etc., but it requires extra work
        }



        if(roles.size() == 0){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This user does not exist. Or user type is restricted.");
        }
        editProfileDTO.setRoles(roles);

        return editProfileDTO;
    }

    public void update(int userId, EditProfileDTO editProfileDTO) {
        if (editProfileDTO.getRoles().contains(UserRole.REGULAR_USER)) {
            RegularUser regularUser = new RegularUser();
            regularUser.setFirstName(editProfileDTO.getFirstName());
            regularUser.setLastName(editProfileDTO.getLastName());
            regularUser.setGender(editProfileDTO.getGender());
            regularUser.setPhoneNumber(editProfileDTO.getPhoneNumber());
            regularUser.setBirthdate(editProfileDTO.getBirthdate());
            regularUser.setAddress(editProfileDTO.getAddress());
            regularUser.setProfileDescription(editProfileDTO.getProfileDescription());
            regularUser.setUserAvatar(editProfileDTO.getUserAvatar());
            regularUserService.update(userId,regularUser);
            // Additional regularUser setters if needed
        } else if (editProfileDTO.getRoles().contains(UserRole.COMPANY)) {
            Company company = new Company();
            company.setCompanyName(editProfileDTO.getCompanyName());
            company.setType(editProfileDTO.getCompanyType());
            company.setEconomicScale(editProfileDTO.getEconomicScale());
            company.setProfileDescription(editProfileDTO.getProfileDescription());
            company.setUserAvatar(editProfileDTO.getUserAvatar());
            companyService.update(userId,company);
            // Additional company setters if needed
        }
        return;
    }


    public void verifyRecruiter(int companyId, int recruiterId) {
        companyService.verifyRecruiter(companyId, recruiterId);
    }
}
