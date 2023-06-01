package com.cs353.backend.service;

import com.cs353.backend.dao.FollowDao;
import com.cs353.backend.model.entities.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public int getNumberOfConnections(int userId) {
        return followDao.getNumberOfConnections(userId);
    }

    public List<Account> getAllFollowers(int userId) {
        return followDao.getAllFollowers(userId);
    }
}
