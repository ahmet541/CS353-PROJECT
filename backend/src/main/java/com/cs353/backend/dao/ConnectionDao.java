package com.cs353.backend.dao;

import com.cs353.backend.model.dto.PostOwnerDTO;
import com.cs353.backend.model.entities.Account;

import java.util.List;

public interface ConnectionDao {
    void sendRequest(int senderId, int receiverId);

    void acceptRequest(int senderId, int receiverId);

    void removeConnection(int senderId, int receiverId);

    boolean connectionExists(int senderId, int receiverId);

    int getNumberOfConnections(int userId);

    List<PostOwnerDTO> getAllConnections(int userId);

    Boolean isPending(int senderId, int receiverId);
}
