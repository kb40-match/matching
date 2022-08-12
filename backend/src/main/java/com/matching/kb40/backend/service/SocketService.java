package com.matching.kb40.backend.service;

import javax.websocket.Session;

import java.util.Set;

public interface SocketService {

    public void onOpen(Session session) throws Exception;

    public void onMessage(String msg) throws Exception;

    public void onClose(Session session) throws Exception;

    public Set<Session> getClients();
}
