package com.example.db.Ite_chat_user.Dao.Controller;

import com.example.db.Ite_chat_user.Dao.Entity.User_chatbot;
import org.springframework.web.bind.annotation.*;

@RestController
public class react_test {




    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/reactuser")
    public User_chatbot usuario(){
        User_chatbot usuario = new User_chatbot("tes48","315789654","99","carmen","carta","5","adminitido");

        return usuario;
    }

    @PostMapping("/recibir")
    public void wpp_send(@RequestBody String json){
        System.out.println(json);

    }



}
