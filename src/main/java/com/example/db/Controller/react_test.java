package com.example.db.Controller;

import com.example.db.Entity.User_chatbot;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class react_test {


    @GetMapping ("/testreact")
    public Message request_message(){
        System.out.println("holis");
        Message message1 = new Message("mensaje que quiero que salga en react","prueba2",3l);
        return message1;


    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/reactuser")
    public User_chatbot usuario(){
        User_chatbot usuario = new User_chatbot("tes48","315789654","99","carmen","carta","5","adminitido");

        return usuario;
    }



}
