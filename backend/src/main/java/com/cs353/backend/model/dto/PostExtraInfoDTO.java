package com.cs353.backend.model.dto;

import com.cs353.backend.model.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostExtraInfoDTO {
    private List<Comment> comments;
    private int likes;
    private boolean likedByUser;
}
