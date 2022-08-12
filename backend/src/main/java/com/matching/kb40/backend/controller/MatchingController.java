package com.matching.kb40.backend.controller;

import com.matching.kb40.backend.dto.MatchDto;
import com.matching.kb40.backend.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
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
}
