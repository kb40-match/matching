package com.matching.kb40.backend.dao;

import com.matching.kb40.backend.dto.MatchDto;
import com.matching.kb40.backend.model.MatchingResult;
import com.matching.kb40.backend.model.UserDataOfMatching;

import java.sql.SQLException;
import java.util.List;

public interface MatchingDao {
    public Integer checkForInsert(MatchDto match) throws SQLException;

    public Integer checkForInsert2(MatchDto match) throws SQLException;

    public void insertForRequest(MatchDto match) throws SQLException;

    public Integer checkForUpdate(MatchDto match) throws SQLException;

    public void updateForAccept(MatchDto match) throws SQLException;

    public void updateForOtherQuit(MatchDto match) throws SQLException;

    public void updateForReject(MatchDto match) throws SQLException;

    public void updateForFinish(MatchDto match) throws SQLException;

    public List<UserDataOfMatching> retrieveReceiver(String senderId) throws SQLException;

    public List<UserDataOfMatching> retrieveSender(String receiverId) throws SQLException;

    public MatchingResult retrieveMatchingResult(String userId) throws SQLException;

    public String retrieveMatchId(String userId) throws SQLException;
}
