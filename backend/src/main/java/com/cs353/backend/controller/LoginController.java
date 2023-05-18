package com.cs353.backend.controller;

import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import com.cs353.backend.service.AccountService;
import com.cs353.backend.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {
    private LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = loginService.authenticate(loginDTO);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }
}
