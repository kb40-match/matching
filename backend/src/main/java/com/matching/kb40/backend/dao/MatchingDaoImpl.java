package com.matching.kb40.backend.dao;

import com.matching.kb40.backend.dto.MatchDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class MatchingDaoImpl implements MatchingDao{
	String ns = "matching.";
    @Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Integer checkForInsert(MatchDto match) throws SQLException {
		return sqlSessionTemplate.selectOne(ns.concat("selectCheckForInsert"), match);
	}

	@Override
	public void insertForRequest(MatchDto match) throws SQLException {
		sqlSessionTemplate.insert(ns.concat("insertForRequest"), match);
	}

	@Override
	public Integer checkForUpdate(MatchDto match) throws SQLException {
		return sqlSessionTemplate.update(ns.concat("selectCheckForUpdate"), match);
	}

	@Override
	public void updateForAccept(MatchDto match) throws SQLException {
		sqlSessionTemplate.update(ns.concat("updateForAccept"), match);
	}

	@Override
	public void updateForOtherQuit(MatchDto match) throws SQLException {
		sqlSessionTemplate.update(ns.concat("updateForOtherQuit"), match);
	}

	@Override
	public void updateForReject(MatchDto match) throws SQLException {
		sqlSessionTemplate.update(ns.concat("updateForReject"), match);
	}

	@Override
	public void updateForFinish(MatchDto match) throws SQLException {
		sqlSessionTemplate.update(ns.concat("updateForFinish"), match);
	}
}
