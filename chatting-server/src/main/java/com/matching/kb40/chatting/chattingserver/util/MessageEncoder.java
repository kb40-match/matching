package com.matching.kb40.chatting.chattingserver.util;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by The Geeky Asian on 1/2/2019.
 */
public class MessageEncoder implements Encoder.Text<String> {


    @Override
    public String encode(String message) throws EncodeException {
        return message;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
        // Close resources (if any used)
    }
}