package com.example.db.Functions_Chatbot.Function_Extra.INTENTION_ANALIZER;

import com.example.db.Chatbot_ISA.Intention.DAO.Intention_Service;
import com.example.db.Chatbot_ISA.Intention.Intentention_Entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class basic_intention {


    @Autowired
    Intention_Service intentionService;


    //devuelve el id de la intencion reconocida
    public Long  intention_exam(String message) {
        List<Intentention_Entity> intentionsList = intentionService.load_intention();

        // Recorrer la lista de intenciones
        for (Intentention_Entity intentions : intentionsList) {
            // Obtener las palabras clave de la intención actual
            String[] keywords = intentions.getReconocimiento().split("\\^");
            // Verificar si el mensaje contiene alguna de las palabras clave
            for (String keyword : keywords) {
                System.out.println(keyword);
                if (message.contains(keyword)) {
                    return intentions.getID();
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
