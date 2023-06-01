package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.JobOpening;
import com.cs353.backend.model.entities.Message;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        int senderId = rs.getInt("sender_id");
        int receiverId = rs.getInt("receiver_id");
        String content = rs.getString("content");
        LocalDateTime postDate = rs.getTimestamp("post_date").toLocalDateTime();

        return new Message(senderId, receiverId, content, postDate);
    }
}
