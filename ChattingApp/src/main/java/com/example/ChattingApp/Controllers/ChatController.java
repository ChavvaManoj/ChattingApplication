package com.example.ChattingApp.Controllers;

import com.example.ChattingApp.Models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/send/{roomId}") // client sends messages to a specific room
    @SendTo("/topic/messages/{roomId}") // broadcast to subscribers of that room
    public ChatMessage send(@DestinationVariable String roomId, ChatMessage message) {
        return message;
    }
}
