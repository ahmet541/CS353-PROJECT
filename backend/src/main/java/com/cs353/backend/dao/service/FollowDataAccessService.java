package com.cs353.backend.dao.service;

import com.cs353.backend.dao.FollowDao;
import com.cs353.backend.mapper.PostOwnerDTOMapper;
import com.cs353.backend.model.dto.PostOwnerDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    }@Override
    public int getNumberOfConnections(int userId) {
        String sql = """
                SELECT COUNT(*)
                FROM follow
                WHERE followed_id = ?
                """;
        int count = jdbcTemplate.queryForObject(sql, Integer.class,userId);
        return count;
    }

    @Override
    public List<PostOwnerDTO> getAllFollowers(int userId) {
        String sql = """
            WITH followers AS (
                SELECT follower_id as id
                FROM follow
                WHERE followed_id = ?
            )
            SELECT P.userId, P.avatar, P.fullName
            FROM post_owner_detail P
            WHERE P.userId IN (SELECT * FROM followers);
            """;

        List<PostOwnerDTO> followers = jdbcTemplate.query(sql, new PostOwnerDTOMapper(), userId);
        return followers;
    }

}
