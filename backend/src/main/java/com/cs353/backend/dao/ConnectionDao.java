package com.cs353.backend.dao;

public interface ConnectionDao {
    void sendRequest(int senderId, int receiverId);

    void acceptRequest(int senderId, int receiverId);

    void removeConnection(int senderId, int receiverId);

    boolean connectionExists(int senderId, int receiverId);
}
