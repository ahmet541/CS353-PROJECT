package com.cs353.backend.dao;

import com.cs353.backend.model.dto.PostExtraInfoDTO;
import com.cs353.backend.model.entities.Post;

import java.util.List;

public interface PostDao {

    List<Post> getAllPostOfConnectionsAndFollows(int id);

    Post createPost(Post post, int userId);

    int getLikeCount(int postId);
    boolean isLikedPost(int postId, int userId);

    void like(int postId, int userId);

    void unlike(int postId, int userId);

    void removePost(int postId);
}
