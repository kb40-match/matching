package com.matching.kb40.backend.controller;

import com.matching.kb40.backend.service.ChattingService;
import com.sun.media.jfxmedia.logging.Logger;

import io.swagger.annotations.ApiOperation;

import java.io.IOException;

import javax.websocket.EncodeException;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.matching.kb40.backend.dto.ChatDto;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/chatting")
public class ChattingController {
	
/* 	@Autowired
	private ChattingService chattingService;

	private final KafkaTemplate kafkaTemplate;
	private final int messagesPerRequest;

	@Autowired
	KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(null);

	public ChattingController(KafkaTemplate kafkaTemplate, @Value("${kafka.message-per-request}") final int messagesPerRequest){
		this.kafkaTemplate = kafkaTemplate;
		this.messagesPerRequest = messagesPerRequest;

	}
	
	@ApiOperation(value = "match_id의 값에 따라 topic 설정")	
    @PostMapping("/partition/{match_id}")
    public ResponseEntity<ChatDto> recieveChat(@PathVariable(value="match_id") String topic, @RequestBody String message, @RequestBody String userId) {

		log.info("---------------------------------------");
		log.info("Receiving message to kafka START!");
		log.debug("Topic: %s",topic);
		log.debug("Message: %s",message);


		log.info("Receiving message to kafka END Successfully!");


		if(!StringUtils.isEmpty(topic) && !StringUtils.isEmpty(message)){
			//kafkaTemplate.re

		}
		//kafkaTemplate.re
		//ChatDto ChatDto = new ChatDto();
//		ChatDto.setSampleData(chatting.getSampleData());




    	
    	return new ResponseEntity<>(ChatDto, HttpStatus.OK);
    } */
}
