package com.cs353.backend.service;

import com.cs353.backend.dao.FollowDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FollowService {

    private final FollowDao followDao;

    public void follow(int followerId, int followedId) {
        followDao.follow(followerId, followedId);
    }

    public void unfollow(int followerId, int followedId) {
        followDao.unfollow(followerId, followedId);
    }
}
