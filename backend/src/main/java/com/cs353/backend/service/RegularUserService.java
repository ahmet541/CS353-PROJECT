package com.cs353.backend.service;

import com.cs353.backend.dao.RegularUserDao;
import com.cs353.backend.mapper.GeneralMapper;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegularUserService {
    private final RegularUserDao regularUserDao;
    private final UserService userService;
    private GeneralMapper generalMapper;

    public int createRegularUser(RegularUser regularUser) {
        User user = generalMapper.mapRegularUserToUser(regularUser);
        int id = userService.createUser(user);
        regularUser.setId(id);
        regularUserDao.createRegularUser(regularUser);
        return id;
    }

    public List<RegularUser> getAllRegularUsers() {
        return regularUserDao.getAllRegularUsers();
    }
}
