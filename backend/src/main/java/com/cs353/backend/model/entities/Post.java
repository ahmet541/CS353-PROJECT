package com.cs353.backend.model.entities;

import com.cs353.backend.Enum.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int postId;

    private int userId;

    private String photoLink = "";

    private String explanation;

    private String heading;

    private LocalDateTime date = LocalDateTime.now();
    private PostType type = PostType.COMMENT;
}
