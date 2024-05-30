package com.example.db.Chatbot_ISA;

import com.example.db.Chatbot_ISA.Intention.DAO.Intention_Service;
import com.example.db.Chatbot_ISA.Intention.Intentention_Entity;
import com.example.db.Chatbot_ISA.Messages.DAO.Message_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.db.Functions_Chatbot.Function_Extra.CONTEXT_ANALAZER.*;

import java.util.List;


@RestController
public class        CentrallController {

    @Autowired
    Context_Analizer contextAnalizer;





    @RequestMapping("/test/context")
    public String  test_context(){
       String id=contextAnalizer.verify_context(1l,"nomina");
        System.out.println(id);
       return id;
    }
















}

