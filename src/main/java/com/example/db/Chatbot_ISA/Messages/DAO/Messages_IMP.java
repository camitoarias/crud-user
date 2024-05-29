package com.example.db.Chatbot_ISA.Messages.DAO;

import com.example.db.Chatbot_ISA.Messages.Messages_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Messages_IMP implements Message_Service {


    @Autowired
    Messages_Repositori messagesRepositori;


    @Override
    public Messages_Entity save_message(Messages_Entity messages) {
        return messagesRepositori.save(messages);


    }

    @Override
    public Optional<Messages_Entity> finById(Long id) {
        return messagesRepositori.findById(id);
    }

    @RequestMapping("/test/message")
    public String test_messages(){
        String[] respuestas={"1","2","3","4","5"};
        Messages_Entity mensaje=new Messages_Entity("BIENVENIDA"," Hola soy el chatbot-ISA soy tu asitente virtual, por favor elige la opcion con la que te quieres comunicar","general","inicio",respuestas);
        save_message(mensaje);
        return "guardado message";
    }



}
