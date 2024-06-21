package com.example.db.Functions_Chatbot.Actions.Functions;

import com.example.db.Functions_Chatbot.Actions.Interfaces.Command;
import com.example.db.Functions_Chatbot.Send_messages.Basic_messages;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("pedir_cedula")
public class pedir_cedula implements Command {


    @Override
    public String execute(String input, User_chatbot usuario) throws IOException {
        String mensaje="por favir digite su numero de cedula";
        System.out.println(mensaje);


        return "cedula pedida";
    }
}
