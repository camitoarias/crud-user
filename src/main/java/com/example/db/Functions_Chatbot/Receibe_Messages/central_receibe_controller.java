package com.example.db.Functions_Chatbot.Receibe_Messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class central_receibe_controller {




    @PostMapping("/recibir")
    public void wpp_send(@RequestBody String json) throws JsonProcessingException {





    }
}
