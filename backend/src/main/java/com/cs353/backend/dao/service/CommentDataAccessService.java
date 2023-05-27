package com.cs353.backend.dao.service;

import com.cs353.backend.dao.CommentDao;
import com.cs353.backend.mapper.CommentMapper;
import com.cs353.backend.mapper.PostMapper;
import com.cs353.backend.model.entities.Comment;
import com.cs353.backend.model.entities.Post;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class CommentDataAccessService implements CommentDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createComment(Comment comment) {
        LocalDateTime currentDate = LocalDateTime.now(); // Get the current date and time

        String sql = """
                INSERT INTO Comment (post_id, user_id, context, date)
                VALUES (?, ?, ?, ?)
                RETURNING comment_id;
                """;
        Object[] params = {comment.getPostId(), comment.getUserId(), comment.getContext(), currentDate};
        int createdCommentId = jdbcTemplate.queryForObject(sql, Integer.class, params);
        return createdCommentId;
    }

    @Override
    public List<Comment> getComments(int postId) {
        String sql = """
                    SELECT * 
                    FROM Comment 
                    WHERE post_id = ? 
                    ORDER BY date ASC
                    """;
        return jdbcTemplate.query(sql, new CommentMapper(), postId);
    }

}
