package com.cs353.backend.dao.service;

import com.cs353.backend.dao.PostDao;
import com.cs353.backend.mapper.RegularUserMapper;
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

                    """;

//        List<RegularUser> regularUsers = jdbcTemplate.query(sql, new RegularUserMapper());

        return null;
    }
}
