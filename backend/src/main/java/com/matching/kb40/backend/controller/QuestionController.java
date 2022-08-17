package com.matching.kb40.backend.controller;

import com.matching.kb40.backend.dto.QuestionDto;
import com.matching.kb40.backend.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://matching.kbfg.kubepia.com")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	public QuestionController(QuestionService questionService){
		this.questionService = questionService;
	}

	@GetMapping("/findAll")
	public List<QuestionDto> findAll() throws Exception {
		List<QuestionDto> questionList = questionService.findAll();

		return questionList;
	}
}
