package com.cs353.backend.dao;

import com.cs353.backend.model.dto.ChatDTO;
import com.cs353.backend.model.entities.Message;

import java.util.List;

public interface MessageDao {
    List<Message> getAll(int senderId, int receiverId);

    List<ChatDTO> getProxyMessages(int userId);


    void removeChat(int user1Id, int user2Id);

    Message createMessage(Message message);
}
