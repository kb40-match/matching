package com.matching.kb40.backend.dao;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.matching.kb40.backend.dto.ChatDto;
import com.matching.kb40.backend.model.ReadChat;

@Repository
public class ChattingDaoImpl implements ChattingDao {
	String nameSapce = "chatting.";

    @Autowired
	SqlSessionTemplate SqlSessionTemplate;

	@Override
	public int insertChat(ChatDto chatDto) throws SQLException{
		int rowCount = SqlSessionTemplate.insert(nameSapce.concat("insertChat"),chatDto);
		return rowCount;
	}

	@Override
	public int updateChat(ReadChat readChat) throws SQLException{
		int rowCount = SqlSessionTemplate.update(nameSapce.concat("cccc"),readChat);
		return rowCount;
	}
}