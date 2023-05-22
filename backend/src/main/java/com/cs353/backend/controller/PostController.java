package com.cs353.backend.controller;

import com.cs353.backend.model.entities.Post;
import com.cs353.backend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @GetMapping("{id}/getAllPostOfConnectionsAndFollows")
    public ResponseEntity<Object> getAllPostOfConnectionsAndFollows(@PathVariable int id) {
        List<Post> posts = postService.getAllPostOfConnectionsAndFollows(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
