package edu.udacity.java.nano.chat;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static ArrayList<String> allMessages = new ArrayList<String>();

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        JSONObject jsonObject = new JSONObject(msg);
        Message message = new Message(jsonObject.get("msg").toString(),
                jsonObject.get("username").toString(),
                "SPEAK",
                onlineSessions.size());
        allMessages.add(message.toJsonStr());
        for (Session s : onlineSessions.values()) {
            try {
                s.getBasicRemote().sendText(message.toJsonStr());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        onlineSessions.put(session.getId(), session);
        String emptyMsg = "{\"msg\": \"\", \"username\": \"\"}";
        sendMessageToAll(emptyMsg);
        for (String message : allMessages) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        sendMessageToAll(jsonStr);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        onlineSessions.remove(session.getId());
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
