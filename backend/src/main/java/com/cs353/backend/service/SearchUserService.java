package com.cs353.backend.service;

import com.cs353.backend.dao.SearchUserDao;
import com.cs353.backend.model.dto.SearchUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchUserService {
    private final SearchUserDao searchUserDao;

    public List<SearchUserDTO> getUsers(String query, String sortOption, String filterOption, String userType) {
        return searchUserDao.getUsers(query,sortOption,filterOption,userType);
    }
}
