package com.cs353.backend.dao;

public interface FollowDao {
    void follow(int followerId, int followedId);

    void unfollow(int followerId, int followedId);
}
