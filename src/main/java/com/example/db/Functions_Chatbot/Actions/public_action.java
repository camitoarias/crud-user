package com.example.db.Functions_Chatbot.Actions;

import com.example.db.Ite_chat_user.Entity.User_chatbot;
import org.springframework.stereotype.Component;

@Component("public_action")
public class public_action implements Command{
    @Override
    public String execute(String input, User_chatbot usuario) {
        return "holi"+input;
    }
}

