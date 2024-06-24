package com.example.db.Actions.Aplication;

import com.example.db.Ite_chat_user.Domain.User_chatbot;
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
