package com.cs353.backend.controller;

import com.cs353.backend.model.dto.PostExtraInfoDTO;
import com.cs353.backend.model.dto.PostOwnerDTO;
import com.cs353.backend.model.entities.Comment;
import com.cs353.backend.model.entities.Post;
import com.cs353.backend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @GetMapping("getAllPostOfConnectionsAndFollows/{userId}")
    public ResponseEntity<List<Post>> getAllPostOfConnectionsAndFollows(@PathVariable int userId) {
        List<Post> posts = postService.getAllPostOfConnectionsAndFollows(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("{userId}")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, @PathVariable int userId) {
        Post newPost = postService.createPost(post,userId);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<String> remove(@PathVariable int postId) {
        postService.removePost(postId);
        return new ResponseEntity<>("Successfully Removed", HttpStatus.OK);
    }

    @GetMapping("{postId}/getPostExtraInfo/{userId}")
    public ResponseEntity<PostExtraInfoDTO> getPostExtraInfo(@PathVariable int postId, @PathVariable int userId) {
        PostExtraInfoDTO postExtraInfo = postService.getPostExtraInfo(postId, userId);
        return new ResponseEntity<>(postExtraInfo, HttpStatus.OK);
    }

    @PostMapping("{postId}/addComment/{userId}")
    public ResponseEntity<String> addComment( @RequestBody Map<String, String> commentData, @PathVariable int postId, @PathVariable int userId) {
        String commentText = commentData.get("comment");
        postService.addComment(commentText, postId, userId);
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable int postId) {
        List<Comment> comments = postService.getComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("{postId}/like/{userId}")
    public ResponseEntity<PostExtraInfoDTO> like(@PathVariable int postId, @PathVariable int userId) {
        PostExtraInfoDTO postExtraInfoDTO = postService.like(postId,userId);
        return new ResponseEntity<>(postExtraInfoDTO, HttpStatus.OK);
    }

    @DeleteMapping("{postId}/unlike/{userId}")
    public ResponseEntity<PostExtraInfoDTO> unlike(@PathVariable int postId, @PathVariable int userId) {
        PostExtraInfoDTO postExtraInfoDTO = postService.unlike(postId,userId);
        return new ResponseEntity<>(postExtraInfoDTO, HttpStatus.OK);
    }
}
