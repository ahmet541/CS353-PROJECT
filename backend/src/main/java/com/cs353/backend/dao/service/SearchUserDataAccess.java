package com.cs353.backend.dao.service;

import com.cs353.backend.dao.SearchUserDao;
import com.cs353.backend.mapper.SearchUserMapper;
import com.cs353.backend.model.dto.SearchUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class SearchUserDataAccess implements SearchUserDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SearchUserDTO> getUsers(String query, String sortOption, String filterOption, String userType) {
        String sql = "";
        List<Object> params = new ArrayList<>();

        if (userType.equals("Regular User")) {
            sql = """
                WITH regular_users AS (
                    SELECT CONCAT_WS(' ', ru.first_name, ru.last_name) AS full_name, u.avatar, u.id AS user_id
                    FROM regular_user ru
                    JOIN "User" u ON ru.id = u.id
                )
                SELECT full_name, avatar, user_id
                FROM regular_users
                """;
        } else if (userType.equals("Company")) {
            sql = """
                WITH companies AS (
                    SELECT c.companyname AS full_name, u.avatar, u.id AS user_id
                    FROM company c
                    JOIN "User" u ON c.id = u.id
                )
                SELECT full_name, avatar, user_id
                FROM companies
                """;
        } else if (userType.equals("All")) {
            sql = """
            WITH all_users AS (
              SELECT full_name, avatar, user_id
              FROM (
                  SELECT CONCAT_WS(' ', ru.first_name, ru.last_name) AS full_name, u.avatar, u.id AS user_id
                  FROM regular_user ru
                  JOIN "User" u ON ru.id = u.id
                  UNION ALL
                  SELECT c.companyname AS full_name, u.avatar, u.id AS user_id
                  FROM company c
                  JOIN "User" u ON c.id = u.id
              ) AS combined_users
            )
            SELECT full_name, avatar, user_id
              FROM all_users
                """;
        }

        if (query != null && !query.isEmpty()) {
            sql += " WHERE full_name ILIKE ? ";
            params.add("%" + query + "%");
        }

        if (filterOption != null && filterOption.equals("name")) {
            sql += "ORDER BY full_name ";
            if (sortOption != null && sortOption.equals("asc")) {
                sql += "ASC";
            } else if (sortOption != null && sortOption.equals("desc")) {
                sql += "DESC";
            }
        }



        List<SearchUserDTO> users = jdbcTemplate.query(
                sql,
                new SearchUserMapper(),
                params.toArray()
        );

        return users;

    }



}
