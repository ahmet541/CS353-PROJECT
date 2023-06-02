package com.cs353.backend.controller;

import com.cs353.backend.model.dto.EditProfileDTO;
import com.cs353.backend.model.dto.EmployDTO;
import com.cs353.backend.model.dto.UserProfileDTO;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.service.EmployService;
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
    private EmployService employService;

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

    @PostMapping("/employ")
    public ResponseEntity<String> employUser(@Valid @RequestBody EmployDTO employDTO) {
        try{
            employService.employUser(employDTO);
            return new ResponseEntity<>("Hired!!", HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{companyId}/fire/{userId}")
    public ResponseEntity<String> fireEmployee(@PathVariable int companyId, @PathVariable  Integer userId) {
        try{
            employService.fireEmployee(companyId, userId);
            return new ResponseEntity<>("Fired!!", HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{companyId}/deleteEmployee/{userId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int companyId, @PathVariable  Integer userId) {
        try{
            employService.deleteEmployee(companyId, userId);
            return new ResponseEntity<>("Fired!!", HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
