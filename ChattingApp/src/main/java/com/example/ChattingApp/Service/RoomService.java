package com.example.ChattingApp.Service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomService {

    // Map<roomId, Set<sessionIds>>
    private final ConcurrentHashMap<String, Set<String>> activeRooms = new ConcurrentHashMap<>();

    public void joinRoom(String roomId, String sessionId) {
        activeRooms.computeIfAbsent(roomId, k -> Collections.newSetFromMap(new ConcurrentHashMap<>())).add(sessionId);
    }

    public void leaveRoom(String roomId, String sessionId) {
        Set<String> users = activeRooms.get(roomId);
        if (users != null) {
            users.remove(sessionId);
            if (users.isEmpty()) {
                activeRooms.remove(roomId); // remove room if empty
            }
        }
    }

    public Set<String> getActiveRooms() {
        return activeRooms.keySet();
    }
}
