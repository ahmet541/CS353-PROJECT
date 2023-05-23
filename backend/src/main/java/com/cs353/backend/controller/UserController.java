package com.cs353.backend.controller;

import com.cs353.backend.model.entities.Post;
import com.cs353.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    UserService userService;
    @GetMapping("{id}/post/others")
    public ResponseEntity<List<Post>> getAllPostOfConnectionsAndFollows(@PathVariable int id) {
        List<Post> posts = userService.getAllPostOfConnectionsAndFollows(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("{id}/post")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, @RequestParam int id) {
        Post newPost = userService.createPost(post,id);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }
}
