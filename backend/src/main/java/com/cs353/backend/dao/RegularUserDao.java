package com.cs353.backend.dao;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.RegularUser;

import java.util.List;

public interface RegularUserDao {
    List<RegularUser> getAllRegularUsers();
    RegularUser getRegularUserById(int id);
    void createRegularUser(RegularUser regularUser);
    void updateRegularUser(RegularUser regularUser);
    void deleteRegularUser(int id);
}

