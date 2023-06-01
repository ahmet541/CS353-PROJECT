package com.cs353.backend.dao.service;

import com.cs353.backend.dao.MessageDao;
import com.cs353.backend.mapper.ChatDTOMapper;
import com.cs353.backend.mapper.MessageMapper;
import com.cs353.backend.model.dto.ChatDTO;
import com.cs353.backend.model.entities.Message;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@AllArgsConstructor
public class MessageDataAccessService implements MessageDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Message> getAll(int senderId, int receiverId) {
        String sql = """
                SELECT *
                FROM message
                WHERE (sender_id = ? AND receiver_id = ?) OR (receiver_id = ? AND sender_id = ?)
                ORDER BY post_date ASC 
                """;
        List<Message> messages = jdbcTemplate.query(sql, new MessageMapper(), senderId, receiverId,  senderId, receiverId);
        return messages;
    }

    @Override
    public List<ChatDTO> getProxyMessages(int userId) {
        String sql = """
        WITH last_messages AS (
            SELECT
                CASE
                    WHEN sender_id = ? THEN receiver_id
                    ELSE sender_id
                END AS other_user_id,
                MAX(post_date) AS max_post_date
            FROM message
            WHERE (sender_id = ? AND receiver_id != ?) OR (sender_id != ? AND receiver_id = ?)
            GROUP BY
                CASE
                    WHEN sender_id = ? THEN receiver_id
                    ELSE sender_id
                END, message.sender_id, message.receiver_id
        )
        SELECT m.*, p.fullName
        FROM (
            SELECT DISTINCT ON (lm.other_user_id)
                m.*, lm.other_user_id
            FROM message m
            INNER JOIN last_messages lm ON
                (m.sender_id = ? AND m.receiver_id = lm.other_user_id) OR
                (m.sender_id = lm.other_user_id AND m.receiver_id = ?)
            ORDER BY lm.other_user_id, m.post_date DESC
        ) m
        JOIN post_owner_detail p ON (m.other_user_id = p.userId)
        ORDER BY m.post_date ASC
        """;
        List<ChatDTO> chats = jdbcTemplate.query(sql, new ChatDTOMapper(), userId, userId, userId, userId, userId, userId, userId, userId);
        return chats;
    }




    @Override
    public void removeChat(int user1Id, int user2Id) {
        String sql = "DELETE FROM message WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)";
        jdbcTemplate.update(sql, user1Id, user2Id, user2Id, user1Id);
    }

    @Override
    public Message createMessage(Message message) {
        String sql = "INSERT INTO message (sender_id, receiver_id, content, post_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, message.getSenderId(), message.getReceiverId(), message.getContent(), message.getPostDate());
        return message;
    }
}
