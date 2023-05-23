package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Post;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PostMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        int postId = rs.getInt("post_id");
        int userId = rs.getInt("user_id");
        String photoLink = rs.getString("photo_link");
        String explanation = rs.getString("explanation");
        String heading = rs.getString("heading");
        LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();

        return new Post(postId, userId, photoLink, explanation, heading, date);
    }
}
