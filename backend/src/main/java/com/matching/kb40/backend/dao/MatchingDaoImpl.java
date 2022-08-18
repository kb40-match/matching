package com.matching.kb40.backend.dao;

import com.matching.kb40.backend.dto.MatchDto;
import com.matching.kb40.backend.model.MatchingResult;
import com.matching.kb40.backend.model.UserDataOfMatching;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

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
	public Integer checkForInsert2(MatchDto match) throws SQLException {
		return sqlSessionTemplate.selectOne(ns.concat("selectCheckForInsert2"), match);
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

	@Override
	public List<UserDataOfMatching> retrieveReceiver(String senderId) throws SQLException {
		return sqlSessionTemplate.selectList(ns.concat("selectReceiverBySenderId"), senderId);
	}

	@Override
	public List<UserDataOfMatching> retrieveSender(String receiverId) throws SQLException {
		return sqlSessionTemplate.selectList(ns.concat("selectSenderByReceiverId"), receiverId);
	}

	@Override
	public MatchingResult retrieveMatchingResult(String userId) throws SQLException {
		return sqlSessionTemplate.selectOne(ns.concat("selectMatchingResult"), userId);
	}

	@Override
	public String retrieveMatchId(String userId) throws SQLException {
		return sqlSessionTemplate.selectOne(ns.concat("selectMatchIdByUserId"), userId);
	}

	@Override
	public MatchDto retrieveMatch(String userId) throws SQLException {
		return sqlSessionTemplate.selectOne(ns.concat("selectMatchByUserId"), userId);
	}
}
