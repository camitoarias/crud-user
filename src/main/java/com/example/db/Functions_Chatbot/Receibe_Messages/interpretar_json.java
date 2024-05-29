package com.example.db.Functions_Chatbot.Receibe_Messages;

import com.example.db.Chatbot_ISA.CentrallController;
import com.example.db.Chatbot_ISA.Intention.DAO.Intention_Service;
import com.example.db.Chatbot_ISA.Intention.Intentention_Entity;
import com.example.db.Chatbot_ISA.Messages.DAO.Message_Service;
import com.example.db.Chatbot_ISA.Messages.Messages_Entity;
import com.example.db.Functions_Chatbot.Send_messages.Basic_messages;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Component
@RestController
public class interpretar_json {



   @Autowired
    User_Service userService;

   @Autowired
    CentrallController centrallController;

   @Autowired
    Intention_Service intentionService;
   @Autowired
   Message_Service messageService;
   @Autowired
    Basic_messages basicMessages;

    @PostMapping("/test/json")
    public void reconocer_json(@RequestBody String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(json);

        JsonNode entryArray = rootNode.path("entry");

        // Asumiendo que quieres acceder al primer objeto del array "entry"
        JsonNode firstEntry = entryArray.get(0);

        // Navega al campo "changes"
        JsonNode changesArray = firstEntry.path("changes");

        // Asumiendo que quieres acceder al primer objeto del array "changes"
        JsonNode firstChange = changesArray.get(0);

        // Navega al campo "value"
        JsonNode valueNode = firstChange.path("value");

        // Navega al campo "messages"
        JsonNode messagesArray = valueNode.path("messages");

        // Asumiendo que quieres acceder al primer objeto del array "messages"
        JsonNode firstMessage = messagesArray.get(0);

        // Navega al campo "text"
        JsonNode textNode = firstMessage.path("text");

        String numberwpp=firstMessage.path("from").asText();
        System.out.println(numberwpp);

        // Obtén el valor del campo "body"
        String body = textNode.path("body").asText();

        // Imprime el valor obtenido

        System.out.println("Body: " + body);

        Long id_intention=User_verify(numberwpp,body);

        if (id_intention!=null){
            Optional<Intentention_Entity> intencion= intentionService.finByid(id_intention);
            System.out.println(intencion.get().getId_message());
            Optional<Messages_Entity> mensaje= messageService.finById(intencion.get().getId_message());
            System.out.println(mensaje.get().getMessage());
            basicMessages.response_ISA(mensaje.get().getMessage());


        }




    }


    public Long User_verify(String numberwpp,String message){
         User_chatbot consultado=userService.findByPhoneNumber(numberwpp);
         if(consultado!=null && consultado.getCONTEXTO()==null){
             System.out.println(consultado.getName());
             Long Id_context=centrallController.intention_exam(message);
             userService.setcontext(Id_context,consultado);
             return  Id_context;
         }
     return null;


    }



    public ResponseEntity<Void> verify_context(Long id_context){

        Optional<Messages_Entity> mensaje_analizar= messageService.finById(id_context);
        System.out.println(mensaje_analizar.get().getRespuestas());
        String respuestas=mensaje_analizar.get().getRespuestas();
        Map<String, List<?>> respuestas2=processInput(respuestas);
        System.out.println(respuestas2);

        Long respuesta_nueva=getCorrespondingLong(respuestas2,"4");
        System.out.println(respuesta_nueva);

        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping ("/test/context")
    public void test(){
        verify_context(1l);

    }

    private Map<String, List<?>> processInput(String input) {
        List<String> normales = new ArrayList<>();
        List<Long> corchetes = new ArrayList<>();

        String[] pairs = input.split(", ");
        for (String pair : pairs) {
            String[] parts = pair.split("\\{");
            String normalValue = parts[0];
            Long corcheteValue = Long.parseLong(parts[1].replace("}", ""));

            normales.add(normalValue);
            corchetes.add(corcheteValue);
        }

        Map<String, List<?>> result = new HashMap<>();
        result.put("normales", normales);
        result.put("corchetes", corchetes);

        return result;
    }

    private Long getCorrespondingLong(Map<String, List<?>> map, String normalValue) {
        List<String> normales = (List<String>) map.get("normales");
        List<Long> corchetes = (List<Long>) map.get("corchetes");

        if (normales.contains(normalValue)) {
            int index = normales.indexOf(normalValue);
            return corchetes.get(index);
        } else {
            return null;  // o lanza una excepción si el valor no se encuentra
        }
    }

}
