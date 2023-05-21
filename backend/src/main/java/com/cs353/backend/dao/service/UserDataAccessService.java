package com.cs353.backend.dao.service;

import com.cs353.backend.dao.UserDao;
import com.cs353.backend.model.entities.User;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserDataAccessService implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void createUser(@NotNull User user) {
        String sql = """
            INSERT INTO "User" (id, profile_description) VALUES (?, ?);
            """;
        jdbcTemplate.update(sql, user.getId(), user.getProfile_description());
    }

    @Override
    public void updateUser(@NotNull User user) {
        String sql = """
            UPDATE "User" SET profile_description = ? WHERE id = ?
            """;
        jdbcTemplate.update(sql, user.getProfile_description(), user.getId());
    }
}
