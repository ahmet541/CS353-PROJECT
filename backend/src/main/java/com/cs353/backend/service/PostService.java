package com.cs353.backend.service;

import com.cs353.backend.dao.PostDao;
import com.cs353.backend.model.dto.PostExtraInfoDTO;
import com.cs353.backend.model.dto.PostOwnerDTO;
import com.cs353.backend.model.entities.Comment;
import com.cs353.backend.model.entities.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final UserService userService;
    CommentService commentService;
    private final PostDao postDao;
    public List<Post> getAllPostOfConnectionsAndFollows(int userId) {
        return postDao.getAllPostOfConnectionsAndFollows(userId);
    }

    public Post createPost(Post post, int userId) {
        return postDao.createPost(post,userId);
    }


    public PostExtraInfoDTO getPostExtraInfo(int postId) {
        PostExtraInfoDTO postExtraInfoDTO = new PostExtraInfoDTO();
        postExtraInfoDTO.setComments(commentService.getComments(postId));
        //TODO
        return postExtraInfoDTO;
    }

    public void addComment(String commentText, int postId, int userId) {
        Comment comment = new Comment();
        comment.setContext(commentText);
        comment.setPostId(postId);
        comment.setUserId(userId);

        int id = commentService.createComment(comment);
    }

    public List<Comment> getComments(int postId) {
        return commentService.getComments(postId);
    }
}
