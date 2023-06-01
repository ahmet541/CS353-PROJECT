package com.cs353.backend.controller;

import com.cs353.backend.model.dto.EditProfileDTO;
import com.cs353.backend.model.dto.UserProfileDTO;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class UserProfileController {
    private UserProfileService userProfileService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDTO> getAccountById(@PathVariable int userId) {
        UserProfileDTO userProfileDTO = userProfileService.getProfileInfo(userId);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}/getEditForm")
    public ResponseEntity<EditProfileDTO> getEditForm(@PathVariable int userId) {
        EditProfileDTO editProfileDTO = userProfileService.getEditForm(userId);
        return new ResponseEntity<>(editProfileDTO, HttpStatus.OK);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<String> update(@PathVariable int userId, @Valid @RequestBody EditProfileDTO editProfileDTO) {
        userProfileService.update(userId,editProfileDTO);
        return new ResponseEntity<>("Update successfull", HttpStatus.OK);
    }

    @PostMapping("/{companyId}/verifyAsRecruiter/{recruiterId}")
    public ResponseEntity<String> update(@PathVariable int companyId, @PathVariable  Integer recruiterId) {
        try{
            userProfileService.verifyRecruiter(companyId, recruiterId);
            return new ResponseEntity<>("Company has a new recruiter now", HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


}
