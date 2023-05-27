package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("comment_id"));
        comment.setPostId(rs.getInt("post_id"));
        comment.setUserId(rs.getInt("user_id"));
        comment.setContext(rs.getString("context"));
        comment.setDate(rs.getTimestamp("date").toLocalDateTime());
        return comment;
    }
}
