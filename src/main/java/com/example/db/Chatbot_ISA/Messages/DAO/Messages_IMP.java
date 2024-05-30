package com.example.db.Chatbot_ISA.Messages.DAO;

import com.example.db.Chatbot_ISA.Messages.Messages_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
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





}
