package com.matching.kb40.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class SocketReqMessage {
    private String socketMessage;
	private String delim;
	private String topicType;
	private String kafkaMessage;

	public SocketReqMessage(String socketMessage, String delim) throws Exception {

		this.socketMessage = socketMessage;
		if (delim == null) {
			this.delim = "|";
		} else {
			this.delim = delim;
		}
		List<String> strToken = new ArrayList<String>();
		try {
			StringTokenizer tokenizer = new StringTokenizer(socketMessage, delim);
			while (tokenizer.hasMoreTokens()) {
				strToken.add(tokenizer.nextToken());
			}
			this.topicType=strToken.get(0);
			this.kafkaMessage=strToken.get(1);
		} catch (Exception e) {
			log.warn("Too Many Topic keyword : {}", socketMessage);			
		}
	}
}