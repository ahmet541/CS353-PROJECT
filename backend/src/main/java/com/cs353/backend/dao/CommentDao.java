package com.cs353.backend.dao;

import com.cs353.backend.model.entities.Comment;

import java.util.List;

public interface CommentDao {

    int createComment(Comment comment);

    List<Comment> getComments(int postId);
}
