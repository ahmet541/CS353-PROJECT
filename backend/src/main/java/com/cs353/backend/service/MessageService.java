package com.cs353.backend.service;

import com.cs353.backend.dao.MessageDao;
import com.cs353.backend.model.dto.ChatDTO;
import com.cs353.backend.model.entities.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageDao messageDao;
    public List<Message> getAll(int senderId, int receiverId) {
        return messageDao.getAll(senderId,receiverId);
    }

    public List<ChatDTO> getProxyMessages(int userId) {
        return messageDao.getProxyMessages(userId);
    }

    public void removeChat(int user1Id, int user2Id) {
        messageDao.removeChat(user1Id, user2Id);
    }

    public Message createMessage(Message message) {

        return messageDao.createMessage(message);
    }
}
