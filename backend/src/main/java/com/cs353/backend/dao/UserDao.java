package com.cs353.backend.dao;

import com.cs353.backend.model.dto.PostOwnerDTO;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.User;

public interface UserDao {

    void createUser(User user);
    void updateUser(User user);

    PostOwnerDTO getPostOwnerInfo(int userId);
}
