package com.example.db.Functions_Chatbot.Function_Extra.USERCHAT_ADD_FUNCTION;

import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class userchatbot_add {
    //esta clase pretende devolver un usuario al controler sobre el cual estar trabajando
    @Autowired
    User_Service userService;


    //verifica si ese numero de wpp ya esta agregado
    public User_chatbot verify_exist(String Phone_number){
        User_chatbot consultado=userService.findByPhoneNumber(Phone_number);
        if(consultado!=null){
            return consultado;
        }else return null;
    }





    //pendiente generar usuario si se encuentra en la lista de exel

    //pendiente obtener el usuario con los datos que se puedan como nombre



}
