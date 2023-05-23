package com.cs353.backend.service;

import com.cs353.backend.dao.AccountDao;
import com.cs353.backend.dao.UserDao;
import com.cs353.backend.mapper.GeneralMapper;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Post;
import com.cs353.backend.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final AccountService accountService;
    PostService postService;
    private final GeneralMapper generalMapper;

    public int createUser(User user) {
        Account account = generalMapper.mapUserToAccount(user);
        int id = accountService.createAccount(account);
        user.setId(id);
        userDao.createUser(user);
        return id;
    }

    public void updateAccount(User user) {
        userDao.updateUser(user);
    }

    public List<Post> getAllPostOfConnectionsAndFollows(int id) {
        return postService.getAllPostOfConnectionsAndFollows(id);
    }

    public Post createPost(Post post, int id) {
        return postService.createPost(post, id);
    }

    public void initalBuild() {
    }
}
