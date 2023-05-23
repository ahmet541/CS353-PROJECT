package com.cs353.backend.service;

import com.cs353.backend.dao.PostDao;
import com.cs353.backend.model.entities.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostDao postDao;
    public List<Post> getAllPostOfConnectionsAndFollows(int id) {
        return postDao.getAllPostOfConnectionsAndFollows(id);
    }

    public Post createPost(Post post, int userId) {
        return postDao.createPost(post,userId);
    }
}
