package com.cs353.backend.dao;

import com.cs353.backend.model.dto.PostOwnerDTO;

import java.util.List;

public interface FollowDao {
    void follow(int followerId, int followedId);

    void unfollow(int followerId, int followedId);

    int getNumberOfConnections(int userId);

    List<PostOwnerDTO> getAllFollowers(int userId);
}
