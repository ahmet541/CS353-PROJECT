package com.cs353.backend.mapper;

import com.cs353.backend.model.dto.ChatDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ChatDTOMapper implements RowMapper<ChatDTO> {

    @Override
    public ChatDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setUserId(rs.getInt("other_user_id"));
        chatDTO.setMessage(rs.getString("content"));
        chatDTO.setFullName(rs.getString("fullName"));
        chatDTO.setTime(rs.getObject("post_date", LocalDateTime.class));
        return chatDTO;
    }
}
