package com.matching.kb40.backend.service;

import com.matching.kb40.backend.dto.MatchDto;
import com.matching.kb40.backend.model.MatchingResult;
import com.matching.kb40.backend.model.UserDataOfMatching;

import java.util.List;

public interface MatchingService {

    public Boolean register(MatchDto match) throws Exception;

    public Boolean modifyAccept(MatchDto match) throws Exception;

    public Boolean modifyReject(MatchDto match) throws Exception;

    public Boolean modifyFinish(MatchDto match) throws Exception;

    public List<UserDataOfMatching> findReceiver(String senderId) throws Exception;

    public List<UserDataOfMatching> findSender(String receiverId) throws Exception;

    public List<MatchingResult> findResult(String userId) throws Exception;

    public String findMatchId(String userId) throws Exception;
}
