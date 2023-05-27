package com.cs353.backend.dao;

import com.cs353.backend.model.dto.PostExtraInfoDTO;
import com.cs353.backend.model.entities.Post;

import java.util.List;

public interface PostDao {

    List<Post> getAllPostOfConnectionsAndFollows(int id);

    Post createPost(Post post, int userId);

}
