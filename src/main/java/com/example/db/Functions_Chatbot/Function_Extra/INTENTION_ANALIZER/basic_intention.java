package com.example.db.Functions_Chatbot.Function_Extra.INTENTION_ANALIZER;

import com.example.db.Chatbot_ISA.Intention.DAO.Intention_Service;
import com.example.db.Chatbot_ISA.Intention.Intentention_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class basic_intention {


    @Autowired
    Intention_Service intentionService;


    //devuelve el id del mensaje siguiente
    public Long  intention_exam(String message) {
        List<Intentention_Entity> intentionsList = intentionService.load_intention();//posibilidad de trasladar el servicio para el central controler

        // Recorrer la lista de intenciones
        for (Intentention_Entity intentions : intentionsList) {
            // Obtener las palabras clave de la intención actual
            String[] keywords = intentions.getReconocimiento().split("\\^");
            // Verificar si el mensaje contiene alguna de las palabras clave
            for (String keyword : keywords) {
                if (message.contains(keyword)) {
                   //SALIDA PARA PUNTO DE CONTROL
                    System.out.println(keyword);
                    //id del siguiente mensaje
                    return intentions.getId_message();
                }
            }
        }return null;
    }




       /* @RequestMapping("/test/chatbot_intention")
    public void intention_load(){
       List<Intentention_Entity> intenciones=intentionService.load_intention();
       System.out.println(intenciones);
       System.out.println(intenciones.get(0).getName());
       Long intention2 = intention_exam("quisiera obtener mi carta bancaria");
        System.out.println(intention2);
    }*/

}
