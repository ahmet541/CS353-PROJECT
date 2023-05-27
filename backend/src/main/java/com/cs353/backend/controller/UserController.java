package com.cs353.backend.controller;

import com.cs353.backend.model.dto.PostOwnerDTO;
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
    @GetMapping("{userId}/getOwner")
    public ResponseEntity<PostOwnerDTO> getOwner(@PathVariable int userId) {
        PostOwnerDTO postOwnerDTO = userService.getOwner(userId);
        return new ResponseEntity<>(postOwnerDTO, HttpStatus.OK);
    }
}
