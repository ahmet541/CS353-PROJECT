package com.cs353.backend.mapper;

import com.cs353.backend.model.dto.PostOwnerDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostOwnerDTOMapper implements RowMapper<PostOwnerDTO> {

    @Override
    public PostOwnerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        String userId = rs.getString("userId");
        String fullName = rs.getString("fullName");
        String avatar = rs.getString("avatar");

        return new PostOwnerDTO(userId, fullName, avatar);
    }
}
