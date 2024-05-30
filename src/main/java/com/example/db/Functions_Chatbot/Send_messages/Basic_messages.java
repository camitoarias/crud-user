package com.example.db.Functions_Chatbot.Send_messages;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.db.Functions_Chatbot.Function_Extra.MESSAGES_FUNCTIONS.genereta_basic_message;


@RestController
public class Basic_messages {



    @Autowired
    genereta_basic_message functions_message;

    @PostMapping("/sendmessage")
    public ResponseEntity<Void> send_message_ISA_Front(@RequestBody String message){
        JSONObject jsonObject=functions_message.basic_message_front(message);
        String json_cadena=jsonObject.toString();
        System.out.println(json_cadena);
        functions_message.sendPostRequest(json_cadena);

         return new ResponseEntity<>( HttpStatus.OK);
    }

    public ResponseEntity<Void> response_ISA(String message){

            functions_message.chatbot_message_send(message);
            return new ResponseEntity<>(HttpStatus.OK);




    }


}
