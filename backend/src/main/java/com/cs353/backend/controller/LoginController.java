package com.cs353.backend.controller;

import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.service.LoginService;
import com.cs353.backend.service.UserService;
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
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = loginService.authenticate(loginDTO);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
//        try {
//            LoginResponseDTO loginResponseDTO = loginService.authenticate(loginDTO);
//            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
//        }
//        catch (Exception ex) {
//            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
//        }
    }


    @PostMapping("registerCompany")
    public ResponseEntity<LoginResponseDTO> registerCompany(@Valid @RequestBody Company company) {
        LoginResponseDTO loginResponseDTO = loginService.registerCompany(company);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        //        try {
//            LoginResponseDTO loginResponseDTO = loginService.registerCompany(company);
//            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            String errorMessage = e.getMessage();
//            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
//        }
    }
    @PostMapping("registerRegularUser")
    public ResponseEntity<LoginResponseDTO> registerRegularUser(@Valid @RequestBody RegularUser regularUser) {
        LoginResponseDTO loginResponseDTO = loginService.registerRegularUser(regularUser);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
//        try {
//            LoginResponseDTO loginResponseDTO = loginService.registerRegularUser(regularUser);
//            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            String errorMessage = e.getMessage();
//            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            // Handle other exceptions if needed
//            String errorMessage = "Registration failed. Please try again.";
//            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }


    @PostMapping("init")
    public void initialBuild() {
        loginService.initialBuild();
    }
}
