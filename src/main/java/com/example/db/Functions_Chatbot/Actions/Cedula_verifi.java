package com.example.db.Functions_Chatbot.Actions;

import com.example.db.Functions_Chatbot.Send_messages.Basic_messages;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("Cedula_verifi")
public class Cedula_verifi implements Command{
  @Autowired
    User_Service userService;
  @Autowired
    Basic_messages basicMessages;

    @Override
    public String execute(String mensaje, User_chatbot usuario) throws IOException {
      //si el usuario tiene cedula verificar cedula
        if(usuario.getCedula()!=null){
          String mensaje1="su cedula es:"+usuario.getCedula();
          basicMessages.response_ISA(mensaje1);

          userService.setcontext(110050l,usuario);
          System.out.println(usuario.getCONTEXTO());

      }else{

            basicMessages.response_ISA("por favor digite su numero de cedula");
            //remitir a funcion que valide ese usuario
            usuario.setCONTEXTO(990020l);
        }
        return "eh finalizado";
    }
}
