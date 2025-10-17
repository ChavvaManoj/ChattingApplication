package com.example.ChattingApp.WebSocket;

import com.example.ChattingApp.Service.RoomService;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.context.event.EventListener;

@Component
public class WebSocketEventListener {

    private final RoomService roomService;
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(RoomService roomService, SimpMessagingTemplate messagingTemplate) {
        this.roomService = roomService;
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String roomId = sha.getFirstNativeHeader("roomId");
        String sessionId = sha.getSessionId();
        if(roomId != null) {
            roomService.joinRoom(roomId, sessionId);
            messagingTemplate.convertAndSend("/topic/rooms", roomService.getActiveRooms());
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = sha.getSessionId();
        roomService.getActiveRooms().forEach(roomId -> {
            roomService.leaveRoom(roomId, sessionId);
        });
        messagingTemplate.convertAndSend("/topic/rooms", roomService.getActiveRooms());
    }
}
