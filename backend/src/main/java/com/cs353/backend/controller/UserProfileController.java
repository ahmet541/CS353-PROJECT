package com.cs353.backend.controller;

import com.cs353.backend.model.dto.UserProfileDTO;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
