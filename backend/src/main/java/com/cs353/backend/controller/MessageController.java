package com.cs353.backend.controller;

import com.cs353.backend.model.dto.ChatDTO;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Message;
import com.cs353.backend.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {
    private MessageService messageService;

    @GetMapping("{senderId}/getAll/{receiverId}")
    public ResponseEntity<List<Message>> getAll(@PathVariable int senderId, @PathVariable int receiverId) {
        List<Message> res = messageService.getAll(senderId,receiverId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{userId}/chats")
    public ResponseEntity<List<ChatDTO>> getChats(@PathVariable int userId) {
        List<ChatDTO> res = messageService.getProxyMessages(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{user1Id}/chat/{user2Id}")
    public ResponseEntity<String> removeChat(@PathVariable int user1Id, @PathVariable int user2Id) {
        messageService.removeChat(user1Id, user2Id);
        return new ResponseEntity<>("Chat removed successfully.", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message messsage = messageService.createMessage(message);
        return new ResponseEntity<>(messsage, HttpStatus.OK);
    }
}
