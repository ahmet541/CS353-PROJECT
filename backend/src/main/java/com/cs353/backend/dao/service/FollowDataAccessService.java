package com.cs353.backend.dao.service;

import com.cs353.backend.dao.FollowDao;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class FollowDataAccessService implements FollowDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void follow(int followerId, int followedId) {
        String sql = """
                INSERT INTO follow (follower_id, followed_id) VALUES (?, ?);
                """;
        jdbcTemplate.update(sql, followerId, followedId);
    }

    @Override
    public void unfollow(int followerId, int followedId) {
        String sql = """
                DELETE FROM follow
                WHERE follower_id = ? AND followed_id = ?;
                """;
        jdbcTemplate.update(sql, followerId, followedId);
    }
}
