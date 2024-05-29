package com.example.db.Chatbot_ISA.Intention;

import com.example.db.Chatbot_ISA.Intention.DAO.Intention_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Intention_controller {
    @Autowired
    Intention_Service intentionService;

}
