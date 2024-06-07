package com.example.db.Functions_Chatbot.Actions;

import com.example.db.Ite_chat_user.Entity.User_chatbot;

import java.io.IOException;

public interface Command {
    String execute(String input, User_chatbot usuario) throws IOException;


}

