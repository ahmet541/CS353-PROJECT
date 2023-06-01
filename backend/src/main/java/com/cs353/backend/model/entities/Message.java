package com.cs353.backend.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private LocalDateTime postDate = LocalDateTime.now();

}
