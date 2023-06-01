package com.cs353.backend.dao;

import com.cs353.backend.model.dto.SearchUserDTO;

import java.util.List;

public interface SearchUserDao {
    List<SearchUserDTO> getUsers(String query, String sortOption, String filterOption, String userType);
}
