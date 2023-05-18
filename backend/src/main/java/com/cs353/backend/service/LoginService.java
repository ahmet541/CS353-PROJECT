package com.cs353.backend.service;

import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private AccountService accountService;
    public LoginResponseDTO authenticate( LoginDTO loginDTO) {
        return accountService.authenticate(loginDTO);
    }
}
