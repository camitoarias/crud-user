package com.example.db.Functions_Chatbot.Actions;

import com.example.db.Functions_Chatbot.Send_messages.Basic_messages;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component("consultar")
public class consultar implements Command{

    @Autowired
    Exel_Controller exelController;
    @Autowired
    Basic_messages basicMessages;
    @Autowired
    User_Service userService;




    @Override
    public String execute(String input, User_chatbot usuario) throws IOException {
       try{

           Map<String,String> admision= exelController.consultar_admision(usuario.getCedula());
           if(admision==null){
               basicMessages.response_ISA("no eh encontrado la cedula");
           }else{
               String mensaje=("eh buscado la cedula"+usuario.getCedula()+"  su estado de admision es:  "+admision.get("estado_admision"));
               basicMessages.response_ISA(mensaje);
               userService.setcontext(null,usuario);
           }


       } catch (IOException e) {




           throw new RuntimeException(e);
       }

        return "funcion terminada";
    }
}
