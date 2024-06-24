package com.example.db.Actions.Aplication;

import com.example.db.Ite_chat_user.Domain.User_chatbot;

import java.io.IOException;

public interface Command {
    String execute(String input, User_chatbot usuario) throws IOException;


}

