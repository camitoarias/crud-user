package com.example.db.Websockettest;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic")
    public Chatmessage caht(@DestinationVariable Chatmessage message ){
        return new Chatmessage(message.getMessage(),message.getUser());
    }
}
