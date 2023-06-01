package com.cs353.backend.service;

import com.cs353.backend.dao.ConnectionDao;
import com.cs353.backend.model.entities.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConnectionService {

    private final ConnectionDao connectionDao;
    public void sendRequest(int senderId, int receiverId) {
        if(connectionDao.connectionExists(senderId,receiverId)){
            throw new IllegalArgumentException("Connection has already existed.");
        }
        connectionDao.sendRequest(senderId, receiverId);
    }

    public void acceptRequest(int senderId, int receiverId) {
        connectionDao.acceptRequest(senderId, receiverId);

    }

    public void removeConnection(int senderId, int receiverId) {
        connectionDao.removeConnection(senderId, receiverId);

    }

    public int getNumberOfConnections(int userId) {
        return connectionDao.getNumberOfConnections(userId);
    }

    public List<Account> getAllConnections(int userId) {
        return connectionDao.getAllConnections(userId);
    }

    public Boolean isPending(int senderId, int receiverId) {
        return connectionDao.isPending(senderId,receiverId);
    }
}
