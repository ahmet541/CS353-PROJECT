package com.cs353.backend.service;

import com.cs353.backend.dao.CommentDao;
import com.cs353.backend.model.entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentDao commentDao;
    public int createComment(Comment comment) {
        return commentDao.createComment(comment);
    }

    public List<Comment> getComments(int postId) {
        return commentDao.getComments(postId);
    }
}
