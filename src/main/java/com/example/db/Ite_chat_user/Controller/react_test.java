package com.example.db.Ite_chat_user.Controller;

import com.example.db.Ite_chat_user.Entity.User_chatbot;
import org.springframework.web.bind.annotation.*;

@RestController
public class react_test {




    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/reactuser")
    public User_chatbot usuario(){
        User_chatbot usuario = new User_chatbot("tes48","315789654","99","carmen","carta","5");
        return usuario;
    }





}
