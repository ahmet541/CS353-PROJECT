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
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = loginService.authenticate(loginDTO);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }

    @PostMapping("registerCompany")
    public ResponseEntity<LoginResponseDTO> registerCompany(@Valid @RequestBody Company company) {

        LoginResponseDTO loginResponseDTO = loginService.registerCompany(company);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }
    @PostMapping("registerRegularUser")
    public ResponseEntity<LoginResponseDTO> registerRegularUser(@Valid @RequestBody RegularUser regularUser) {

        LoginResponseDTO loginResponseDTO = loginService.registerRegularUser(regularUser);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }
}
