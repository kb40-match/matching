package com.matching.kb40.backend.service;

import com.matching.kb40.backend.dao.MatchingDao;
import com.matching.kb40.backend.dto.MatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MatchingServiceImpl implements MatchingService{

    @Autowired
	private MatchingDao matchingDao;

	@Override
	public Boolean register(MatchDto match) throws Exception {
		if(matchingDao.checkForInsert(match) > 0) {
			// 상대(receiver)가 이미 대화중
			return false;
		}
		else{
			matchingDao.insertForRequest(match);
			// 대화 신청 완료
			return true;
		}
	}

	@Override
	public Boolean modifyAccept(MatchDto match) throws Exception {
		if (matchingDao.checkForUpdate(match) > 0) {
			// 상대(sender)가 이미 대화중
			return false;
		} else {
			matchingDao.updateForAccept(match);
			matchingDao.updateForOtherQuit(match);

			// 대화 수락 완료
			return true;
		}
	}

	@Override
	public Boolean modifyReject(MatchDto match) throws Exception {
		matchingDao.updateForReject(match);

		return true;
	}

	@Override
	public Boolean modifyFinish(MatchDto match) throws Exception {
		matchingDao.updateForFinish(match);

		return true;
	}
}
