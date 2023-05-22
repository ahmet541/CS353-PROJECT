package com.cs353.backend.controller;

import com.cs353.backend.model.entities.Post;
import com.cs353.backend.service.FollowService;
import com.cs353.backend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@AllArgsConstructor
public class FollowController {
    private FollowService followService;

    @PostMapping("{followerId}/{followedId}")
    public ResponseEntity<Object> follow(@PathVariable int followerId, @PathVariable int followedId) {
        followService.follow(followerId,followedId);
        return new ResponseEntity<>("User followed successfully.", HttpStatus.OK);
    }

    @DeleteMapping("{followerId}/{followedId}")
    public ResponseEntity<Object> unfollow(@PathVariable int followerId, @PathVariable int followedId) {
        followService.unfollow(followerId,followedId);
        return new ResponseEntity<>("User unfollowed successfully.", HttpStatus.OK);
    }
}
