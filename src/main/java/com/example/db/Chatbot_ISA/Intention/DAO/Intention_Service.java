package com.example.db.Chatbot_ISA.Intention.DAO;

import com.example.db.Chatbot_ISA.Intention.Intentention_Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface Intention_Service {

    Intentention_Entity save_intention(Intentention_Entity intention);

    List<Intentention_Entity> load_intention();

    Optional<Intentention_Entity> finByid(Long id);

}
