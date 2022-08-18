package com.matching.kb40.backend.controller;

import com.matching.kb40.backend.dto.MatchDto;
import com.matching.kb40.backend.model.MatchingResult;
import com.matching.kb40.backend.model.UserDataOfMatching;
import com.matching.kb40.backend.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/matching")
public class MatchingController {

	@Autowired
	private MatchingService matchingService;

    @PostMapping("/request")
	public Boolean register(@RequestBody MatchDto match) throws Exception {
		return matchingService.register(match);
	}

	@PutMapping("/accept")
	public Boolean modifyAccept(@RequestBody MatchDto match) throws Exception {
		return matchingService.modifyAccept(match);
	}

	@PutMapping("/reject")
	public Boolean modifyReject(@RequestBody MatchDto match) throws Exception {
		return matchingService.modifyReject(match);
	}

	@PutMapping("/finish")
	public Boolean modifyFinish(@RequestBody MatchDto match) throws Exception {
		return matchingService.modifyFinish(match);
	}

	@GetMapping("/receivers/{senderId}")
	public List<UserDataOfMatching> findReceiver(@PathVariable String senderId) throws Exception {
		return matchingService.findReceiver(senderId);
	}

	@GetMapping("/senders/{receiverId}")
	public List<UserDataOfMatching> findSender(@PathVariable String receiverId) throws Exception {
		return matchingService.findSender(receiverId);
	}

	@GetMapping("/result/{userId}")
	public List<MatchingResult> findResult(@PathVariable String userId) throws Exception {
		return matchingService.findResult(userId);
	}

	@GetMapping("/matchId/{userId}")
	public String findMatchId(@PathVariable String userId) throws Exception {
		return matchingService.findMatchId(userId);
	}

	@GetMapping("/match/{userId}")
	public MatchDto findMatch(@PathVariable String userId) throws Exception {
		return matchingService.findMatch(userId);
	}

}
