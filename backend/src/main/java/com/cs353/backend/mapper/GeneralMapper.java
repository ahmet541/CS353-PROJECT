package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class GeneralMapper {

    public Account mapRegularUserToAccount(RegularUser regularUser) {
        Account account = new Account();
        account.setEmail(regularUser.getEmail());
        account.setPassword(regularUser.getPassword());

        return account;
    }

    public Account mapCompanyToAccount(Company company) {
        Account account = new Account();
        account.setEmail(company.getEmail());
        account.setPassword(company.getPassword());

        return account;
    }

    public Account mapUserToAccount(User user) {
        Account account = new Account();
        account.setEmail(user.getEmail());
        account.setPassword(user.getPassword());

        return account;
    }

    public User mapRegularUserToUser(RegularUser regularUser) {
        User user = new User();
        user.setEmail(regularUser.getEmail());
        user.setPassword(regularUser.getPassword());
        user.setProfileDescription(regularUser.getProfileDescription());

        return user;
    }
}
