package com.matching.kb40.backend.dao;

import com.matching.kb40.backend.dto.QuestionDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao{
	String ns = "question.";

    @Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<QuestionDto> retrieveAll() throws SQLException {
		return sqlSessionTemplate.selectList(ns.concat("selectAll"));
	}
}
