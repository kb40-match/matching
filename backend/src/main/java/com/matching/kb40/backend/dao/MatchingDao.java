package com.matching.kb40.backend.dao;

import com.matching.kb40.backend.dto.MatchDto;

import java.sql.SQLException;

public interface MatchingDao {
    public Integer checkForInsert(MatchDto match) throws SQLException;

    public void insertForRequest(MatchDto match) throws SQLException;

    public Integer checkForUpdate(MatchDto match) throws SQLException;

    public void updateForAccept(MatchDto match) throws SQLException;

    public void updateForOtherQuit(MatchDto match) throws SQLException;

    public void updateForReject(MatchDto match) throws SQLException;

    public void updateForFinish(MatchDto match) throws SQLException;
}
