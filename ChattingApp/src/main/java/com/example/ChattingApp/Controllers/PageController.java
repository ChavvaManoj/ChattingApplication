package com.example.ChattingApp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/chatroom/{roomId}"})
    public String index() {
        return "forward:/index.html";
    }
}
