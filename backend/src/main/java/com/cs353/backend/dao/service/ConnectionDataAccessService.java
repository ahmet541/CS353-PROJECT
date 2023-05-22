package com.cs353.backend.dao.service;

import com.cs353.backend.dao.ConnectionDao;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
