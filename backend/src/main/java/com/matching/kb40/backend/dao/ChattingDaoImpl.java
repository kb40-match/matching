package com.matching.kb40.backend.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.matching.kb40.backend.model.Chat;

@Repository
public class ChattingDaoImpl implements ChattingDao {
    @Autowired
	SqlSessionTemplate SqlSessionTemplate;

	@Override
	public Chat chattingTest() {
		int sampleData = SqlSessionTemplate.selectOne("chatting.chattingTest");
		Chat chatting = new Chat();

		chatting.setSampleData(sampleData);


//		logger.debug("Matching >>>>>>>" + chatting.toString());

		return chatting;

	}

}
