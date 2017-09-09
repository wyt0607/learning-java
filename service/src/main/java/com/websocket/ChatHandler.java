package com.websocket;

import com.enums.EnumTypes;
import com.service.impl.RedisService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatHandler implements WebSocketHandler {

    @Autowired
    private RedisService redisService;

    private static Map<String, WebSocketSession> websocketMap = new HashMap();

    private Logger logger = LoggerFactory.getLogger(ChatHandler.class);


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = session.getUri().getQuery();
        websocketMap.put(userId, session);
        userOnline();
        logger.info("websocket 建立成功");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        JSONObject jsonObject = new JSONObject(message.getPayload().toString());
        logger.info(jsonObject.toString());
        Date temp = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
        String date = dateFormat.format(temp);
        jsonObject.put("date", date);
        jsonObject.put("time", temp.getTime());
        TextMessage textMessage = new TextMessage(jsonObject.toString().getBytes("UTF-8"));
        logger.info(textMessage.getPayload());
        if ("sendAll".equals(jsonObject.get("targetId").toString())) {
            sendAll(textMessage);
        } else {
            sendOne(jsonObject.get("targetId").toString(), textMessage);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("websocket 连接失败");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        websocketMap.remove(session.getUri().getQuery());
        userOnline();
        logger.info("websocket 连接关闭");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public static void sendAll(WebSocketMessage<?> message) {
        websocketMap.forEach((k, v) -> {
            try {
                v.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendOne(String userId, WebSocketMessage<?> message) {
        WebSocketSession session = websocketMap.get(userId);
        try {
            if (session != null) {
                session.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userOnline() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userList", JSONObject.wrap(websocketMap.keySet()).toString());
        jsonObject.put("type", EnumTypes.SET_USERLIST);
        WebSocketMessage message = new TextMessage(jsonObject.toString().getBytes("UTF-8"));
        websocketMap.forEach((k, v) -> {
            try {
                v.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
