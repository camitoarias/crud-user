package com.example.db.Functions_Chatbot.Actions;

import com.example.db.Functions_Chatbot.Send_messages.Basic_messages;
import com.example.db.GH_gestion.Exel_Conection.Exel_Controller;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component("tomar_cedula")
public class tomar_cedula implements  Command{
  @Autowired
    Exel_Controller exelController;
  @Autowired
    Basic_messages basicMessages;
  @Autowired
    User_Service userService;

    @Override
    public String execute(String input, User_chatbot usuario) throws IOException {
        String cedula_limpia=limpiarIdentificacion(input);
        System.out.println("cedula limpia"+cedula_limpia);
        try{

            Map<String,String> admision=exelController.consultar_admision(cedula_limpia);
            if (admision==null){
                basicMessages.response_ISA("no eh encontrado la cedula");
            }else{
                String mensaje=("eh buscado la cedula"+usuario.getCedula()+"  su estado de admision es:  "+admision.get("estado_admision"));
                basicMessages.response_ISA(mensaje);
                userService.setcontext(null,usuario);


            }

        } catch (Exception e) {
            basicMessages.response_ISA("eh tenido problemas lo siento");
            throw new RuntimeException(e);
        }


        return "ya tome la cedula";
    }

    public static String limpiarIdentificacion(String identificacion) {
        // Usar una expresión regular para reemplazar todo lo que no sea un dígito
        return identificacion.replaceAll("\\D+", "");
    }
}
