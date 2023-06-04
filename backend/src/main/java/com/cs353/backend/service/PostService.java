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
        post.setUserId(userId);
        return postDao.createPost(post,userId);
    }


    public PostExtraInfoDTO getPostExtraInfo(int postId, int userId) {
        PostExtraInfoDTO postExtraInfoDTO = new PostExtraInfoDTO();
        postExtraInfoDTO.setComments(commentService.getComments(postId));
        postExtraInfoDTO.setLikes(postDao.getLikeCount(postId));
        postExtraInfoDTO.setLikedByUser(postDao.isLikedPost(postId,userId));
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

    public PostExtraInfoDTO like(int postId, int userId) {
        postDao.like(postId,userId);
        PostExtraInfoDTO postExtraInfoDTO = new PostExtraInfoDTO();
        postExtraInfoDTO.setLikes(postDao.getLikeCount(postId));
        postExtraInfoDTO.setLikedByUser(postDao.isLikedPost(postId,userId));
        return postExtraInfoDTO;
    }

    public PostExtraInfoDTO unlike(int postId, int userId) {
        postDao.unlike(postId,userId);
        PostExtraInfoDTO postExtraInfoDTO = new PostExtraInfoDTO();
        postExtraInfoDTO.setLikes(postDao.getLikeCount(postId));
        postExtraInfoDTO.setLikedByUser(postDao.isLikedPost(postId,userId));
        return postExtraInfoDTO;
    }

    public void removePost(int postId) {
        postDao.removePost(postId);
    }
}
