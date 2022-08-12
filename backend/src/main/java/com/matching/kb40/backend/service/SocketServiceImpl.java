package com.matching.kb40.backend.service;

import com.matching.kb40.backend.dao.ChattingDao;
import com.matching.kb40.backend.dto.ChatDto;
import com.matching.kb40.backend.model.NewChat;
import com.matching.kb40.backend.model.ReadChat;
import com.matching.kb40.backend.model.SocketReqMessage;
import com.matching.kb40.backend.util.MessageDecoder;
import com.matching.kb40.backend.util.MessageEncoder;

import java.net.Socket;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@ServerEndpoint(value="/chatWebSocket", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
@Slf4j
@Service
public class SocketServiceImpl implements SocketService{

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("open session : " + session.toString());
		if(!clients.contains(session)) {
			clients.add(session);
			System.out.println("session open : " + session);
		}else {
			System.out.println("이미 연결된 session 임!!!");
		}
	}

	@OnMessage
	public void onMessage(String msg) throws Exception{
		SocketReqMessage socketReqMessage = new SocketReqMessage(msg,"|");
		if("insert".equals(socketReqMessage.getTopicType())){
			kafkaTemplate.send("${param.kafka.topic.total.insert}",socketReqMessage.getKafkaMessage());
		}else if("update".equals(socketReqMessage.getTopicType())){
			kafkaTemplate.send("${param.kafka.topic.total.update}",socketReqMessage.getKafkaMessage());
		}
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("session close : " + session);
		clients.remove(session);
	}

	public Set<Session> getClients(){
		return clients;
	}

}
