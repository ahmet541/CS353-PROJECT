package com.cs353.backend.mapper;

import com.cs353.backend.dao.SearchUserDao;
import com.cs353.backend.model.dto.SearchUserDTO;
import com.cs353.backend.model.entities.CareerExpert;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchUserMapper implements RowMapper<SearchUserDTO> {

    @Override
    public SearchUserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        SearchUserDTO user = new SearchUserDTO();
        user.setFullName(rs.getString("full_name"));
        user.setAvatar(rs.getString("avatar"));
        user.setUserId(rs.getInt("user_id"));
        return user;
    }


}
