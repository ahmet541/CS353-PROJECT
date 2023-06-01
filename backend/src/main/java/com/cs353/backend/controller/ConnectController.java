package com.cs353.backend.controller;

import com.cs353.backend.model.dto.PostOwnerDTO;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.service.ConnectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connect")
@AllArgsConstructor
public class ConnectController {

    private ConnectionService connectionService;

    @PostMapping("{senderId}/sendRequest/{receiverId}")
    public ResponseEntity<List<PostOwnerDTO>> sendRequest(@PathVariable int senderId, @PathVariable int receiverId) {
        connectionService.sendRequest(senderId, receiverId);
        List<PostOwnerDTO> res = connectionService.getAllConnections(receiverId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("{senderId}/acceptRequest/{receiverId}")
    public ResponseEntity<Object> acceptRequest(@PathVariable int senderId, @PathVariable int receiverId) {
        connectionService.acceptRequest(senderId, receiverId);
        return new ResponseEntity<>("Connection request accepted successfully.", HttpStatus.OK);
    }

    @DeleteMapping("{senderId}/removeConnection/{receiverId}")
    public ResponseEntity<List<PostOwnerDTO>> removeConnection(@PathVariable int senderId, @PathVariable int receiverId) {
        connectionService.removeConnection(senderId, receiverId);
        List<PostOwnerDTO> res = connectionService.getAllConnections(receiverId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("{userId}/allConnections")
    public ResponseEntity<Object> getAllConnections(@PathVariable int userId) {
        Object o = connectionService.getAllConnections(userId);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
    @GetMapping("{senderId}/isPending/{receiverId}")
    public ResponseEntity<Boolean> isPending(@PathVariable int senderId, @PathVariable int receiverId) {
        Boolean res = connectionService.isPending(senderId,receiverId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
