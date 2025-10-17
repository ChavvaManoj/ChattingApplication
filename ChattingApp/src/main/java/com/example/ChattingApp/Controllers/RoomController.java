package com.example.ChattingApp.Controllers;

import com.example.ChattingApp.Service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public Map<String, Object> getRooms() {
        return Map.of(
                "activeRoomCount", roomService.getActiveRooms(),
                "rooms", roomService.getActiveRooms()
        );
    }
}
