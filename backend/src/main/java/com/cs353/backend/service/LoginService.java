package com.cs353.backend.service;

import com.cs353.backend.Constants;
import com.cs353.backend.Enum.UserRole;
import com.cs353.backend.dao.service.AdminDataAccessService;
import com.cs353.backend.mapper.GeneralMapper;
import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private AccountService accountService;
    private UserService userService;
    private RegularUserService regularUserService;
    private CompanyService companyService;
    private GeneralMapper generalMapper;
    private AdminDataAccessService adminService;

    public LoginResponseDTO authenticate( LoginDTO loginDTO) {
        return accountService.authenticate(loginDTO);
    }

    public LoginResponseDTO registerCompany(Company company) {
        if (accountService.isEmailUsed(company.getEmail())) {
            throw new IllegalArgumentException(Constants.EMAIL_IN_USE_ERROR_MESSAGE);
        }
        else{
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            int id = companyService.createCompany(company);
            loginResponseDTO.setId(id);
            loginResponseDTO.setRole(UserRole.COMPANY);

            return loginResponseDTO;
        }
    }
    public LoginResponseDTO registerRegularUser(RegularUser regularUser) {
        if (accountService.isEmailUsed(regularUser.getEmail())) {
            throw new IllegalArgumentException(Constants.EMAIL_IN_USE_ERROR_MESSAGE);
        }
        else{
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            int id = regularUserService.createRegularUser(regularUser);
            loginResponseDTO.setId(id);
            loginResponseDTO.setRole(UserRole.REGULAR_USER);

            return loginResponseDTO;
        }

    }

    public void initialBuild() {
        regularUserService.initialBuild();
        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword("pw");
        int id = userService.createUser(admin);
        admin.setId(id);
        adminService.createAdmin(admin, "admin");
        //TODO for others
    }
}
