package com.cs353.backend.controller;

import com.cs353.backend.model.dto.SearchUserDTO;
import com.cs353.backend.service.SearchUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search-user")
@AllArgsConstructor
public class SearchUserController {

    private SearchUserService searchUserService;

    @GetMapping()
    public List<SearchUserDTO> getUsers(
            @RequestParam(name = "searchQuery", required = false) String searchQuery,
            @RequestParam(name = "sort", required = false) String sortOption,
            @RequestParam(name = "field", required = false) String filterOption,
            @RequestParam(name = "userType", required = false) String userType
    ) {
        // Pass the query, sortOption, filterOption, and userType to the userService
        return searchUserService.getUsers(searchQuery, sortOption, filterOption, userType);
    }
}
