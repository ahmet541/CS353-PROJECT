package com.cs353.backend.controller;

import com.cs353.backend.service.ConnectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connect")
@AllArgsConstructor
public class ConnectController {

    private ConnectionService connectionService;

    @PostMapping("{senderId}/sendRequest/{receiverId}")
    public ResponseEntity<Object> sendRequest(@PathVariable int senderId, @PathVariable int receiverId) {
        connectionService.sendRequest(senderId, receiverId);
        return new ResponseEntity<>("Connection request sent successfully.", HttpStatus.OK);
    }

    @PostMapping("{senderId}/acceptRequest/{receiverId}")
    public ResponseEntity<Object> acceptRequest(@PathVariable int senderId, @PathVariable int receiverId) {
        connectionService.acceptRequest(senderId, receiverId);
        return new ResponseEntity<>("Connection request accepted successfully.", HttpStatus.OK);
    }

    @DeleteMapping("{senderId}/removeConnection/{receiverId}")
    public ResponseEntity<Object> removeConnection(@PathVariable int senderId, @PathVariable int receiverId) {
        connectionService.removeConnection(senderId, receiverId);
        return new ResponseEntity<>("Connection removed successfully.", HttpStatus.OK);
    }
}
