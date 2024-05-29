package com.example.db.Chatbot_ISA.Messages.DAO;

import com.example.db.Chatbot_ISA.Messages.Messages_Entity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface Message_Service {

    Messages_Entity save_message(Messages_Entity mensaje);

    Optional<Messages_Entity> finById(Long id);


}
