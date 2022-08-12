package com.matching.kb40.backend.service;

import com.matching.kb40.backend.dto.MatchDto;

public interface MatchingService {

    public Boolean register(MatchDto match) throws Exception;

    public Boolean modifyAccept(MatchDto match) throws Exception;

    public Boolean modifyReject(MatchDto match) throws Exception;

    public Boolean modifyFinish(MatchDto match) throws Exception;
}
