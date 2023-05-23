package com.cs353.backend.dao.service;

import com.cs353.backend.dao.PostDao;
import com.cs353.backend.mapper.AccountMapper;
import com.cs353.backend.mapper.PostMapper;
import com.cs353.backend.mapper.RegularUserMapper;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Post;
import com.cs353.backend.model.entities.RegularUser;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
public class PostDataAccessService implements PostDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Post> getAllPostOfConnectionsAndFollows(int id) {
        String sql = """
                    WITH connections AS (
                        SELECT (CASE WHEN connected_1_id = ? THEN connected_2_id ELSE connected_1_id END) AS id
                        FROM connection
                        WHERE (connected_1_id = ? OR connected_2_id = ?) AND accepted = true
                    ),
                    follows AS (
                        SELECT followed_id as id
                        FROM follow
                        WHERE follower_id = ?
                    )
                    SELECT *
                    FROM Post
                    WHERE user_id IN (
                                    SELECT id FROM connections
                                    UNION
                                    SELECT id FROM follows
                                    UNION
                                    SELECT ?
                    )
                    ORDER BY date DESC;
                    """;

        List<Post> posts = jdbcTemplate.query(sql, new PostMapper(), id, id, id, id, id);

        return posts;
    }

    @Override
    public Post createPost(Post post, int userId) {
        String sql = """
                    INSERT INTO Post (user_id, photo_link, explanation, heading, date) VALUES ( ?, ?, ?, ?, ?)
                    RETURNING post_id;
                    """;
        Post createdPost = jdbcTemplate.queryForObject(sql, new PostMapper(), userId, post.getPhotoLink(), post.getExplanation(), post.getHeading(), post.getDate());

        return createdPost;
//        Integer postId = jdbcTemplate.queryForObject(sql, Integer.class, userId, post.getPhotoLink(), post.getExplanation(), post.getHeading(), post.getDate());
//        //TODO find id
//        return post;
    }
}
