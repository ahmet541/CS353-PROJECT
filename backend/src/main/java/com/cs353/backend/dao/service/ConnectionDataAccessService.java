package com.cs353.backend.dao.service;

import com.cs353.backend.dao.ConnectionDao;
import com.cs353.backend.mapper.AccountMapper;
import com.cs353.backend.mapper.PostOwnerDTOMapper;
import com.cs353.backend.model.dto.PostOwnerDTO;
import com.cs353.backend.model.entities.Account;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ConnectionDataAccessService implements ConnectionDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void sendRequest(int senderId, int receiverId) {
        String sql = """
                INSERT INTO connection (connected_1_id, connected_2_id) VALUES (?, ?);
                """;
        jdbcTemplate.update(sql, senderId, receiverId);
    }

    @Override
    public void acceptRequest(int senderId, int receiverId) {
        String sql = """
                UPDATE connection SET accepted = true
                WHERE connected_1_id = ? AND connected_2_id = ?
                """;
        jdbcTemplate.update(sql, senderId, receiverId);
    }

    @Override
    public void removeConnection(int senderId, int receiverId) {
        String sql = """
            DELETE FROM connection
            WHERE (connected_1_id, connected_2_id) IN ((?, ?), (?, ?));
            """;
        jdbcTemplate.update(sql, senderId, receiverId, receiverId, senderId);

    }
    @Override
    public boolean connectionExists(int senderId, int receiverId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM connection WHERE (connected_1_id, connected_2_id) IN ((?, ?), (?, ?)))";
        boolean exists = jdbcTemplate.queryForObject(sql, Boolean.class, senderId, receiverId, receiverId, senderId);
        return exists;
    }

    @Override
    public int getNumberOfConnections(int userId) {
        String sql = """
                WITH connected_ids AS (
                    SELECT (CASE WHEN connected_1_id = ? THEN connected_2_id ELSE connected_1_id END) AS id
                    FROM connection
                    WHERE (connected_1_id = ? OR connected_2_id = ?) AND accepted = true
                )
                SELECT COUNT(*)
                FROM connected_ids
                """;
        int count = jdbcTemplate.queryForObject(sql, Integer.class,userId,userId,userId);
        return count;

    }

    @Override
    public List<PostOwnerDTO> getAllConnections(int userId) {
        String sql = """
            WITH connectedUsers AS (
                SELECT (CASE WHEN connected_1_id = ? THEN connected_2_id ELSE connected_1_id END) AS id
                FROM connection
                WHERE (connected_1_id = ? OR connected_2_id = ?) AND accepted = true
            )
            SELECT P.userId, P.avatar, P.fullName
            FROM post_owner_detail P
            WHERE P.userId IN (SELECT * FROM connectedUsers);
            """;

        List<PostOwnerDTO> accounts = jdbcTemplate.query(sql, new PostOwnerDTOMapper(), userId, userId, userId);
        return accounts;
    }

    @Override
    public Boolean isPending(int senderId, int receiverId) {
        String sql = """
            SELECT EXISTS(
                SELECT 1 
                FROM connection 
                WHERE (connected_1_id, connected_2_id) IN ((?, ?), (?, ?)) AND accepted = false
                )
            """;
        boolean exists = jdbcTemplate.queryForObject(sql, Boolean.class, senderId, receiverId, receiverId, senderId);
        return exists;
    }

}
