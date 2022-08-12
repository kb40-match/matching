package com.matching.kb40.backend.dao;

import com.matching.kb40.backend.dto.MydataDto;
import com.matching.kb40.backend.dto.UserDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
	String ns = "user.";

    @Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public UserDto retrieve(String userId) throws SQLException {
		return sqlSessionTemplate.selectOne(ns.concat("selectByUserId"), userId);
	}

	@Override
	public List<UserDto> retrieveAll() throws SQLException {
		return sqlSessionTemplate.selectList(ns.concat("selectAll"));
	}

	@Override
	public void update(UserDto user) throws SQLException {
		sqlSessionTemplate.update(ns.concat("updateByUserId"), user);
	}

	@Override
	public void delete(String userId) throws SQLException {
		sqlSessionTemplate.delete(ns.concat("deleteByUserId"), userId);
	}

	@Override
	public MydataDto retrieveMydata(String userId) throws SQLException {
		return sqlSessionTemplate.selectOne(ns.concat("selectMydataByUserId"), userId);
	}


}
