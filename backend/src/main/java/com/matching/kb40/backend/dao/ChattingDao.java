package com.matching.kb40.backend.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.matching.kb40.backend.dto.ChatDto;
import com.matching.kb40.backend.model.ReadChat;

public interface ChattingDao {

    public int insertChat(ChatDto chatDto) throws SQLException;

    public int updateChat(ReadChat readChat) throws SQLException;
}
