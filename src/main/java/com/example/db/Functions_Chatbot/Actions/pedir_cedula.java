package com.example.db.Functions_Chatbot.Actions;

import com.example.db.Functions_Chatbot.Send_messages.Basic_messages;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("pedir_cedula")
public class pedir_cedula implements Command{
    @Autowired
    User_Service userService;
    @Autowired
    Basic_messages basicMessages;
    @Override
    public String execute(String input, User_chatbot usuario) throws IOException {
        String mensaje="por favir digite su numero de cedula";
        System.out.println(mensaje);
        basicMessages.response_ISA(mensaje);
        userService.setcontext(990020l,usuario);
        return "cedula pedida";
    }
}
