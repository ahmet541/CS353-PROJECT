package com.cs353.backend.controller;

import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {
    private LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            LoginResponseDTO loginResponseDTO = loginService.authenticate(loginDTO);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("registerCompany")
    public ResponseEntity<Object> registerCompany(@Valid @RequestBody Company company) {
        try {
            LoginResponseDTO loginResponseDTO = loginService.registerCompany(company);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }
    }
    @PostMapping("registerRegularUser")
    public ResponseEntity<Object> registerRegularUser(@Valid @RequestBody RegularUser regularUser) {
        try {
            LoginResponseDTO loginResponseDTO = loginService.registerRegularUser(regularUser);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Handle other exceptions if needed
            String errorMessage = "Registration failed. Please try again.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
