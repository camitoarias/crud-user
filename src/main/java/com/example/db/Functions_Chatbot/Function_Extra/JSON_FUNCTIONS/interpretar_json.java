package com.example.db.Functions_Chatbot.Function_Extra.JSON_FUNCTIONS;

import com.example.db.Functions_Chatbot.Send_messages.Basic_messages;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class interpretar_json {










    //pendiente de cambio en los retornos
    public String[] reconocer_json(@RequestBody String json) throws JsonProcessingException {

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

        String[] retornar={body,numberwpp};
        return retornar ; }


    public  String  extract_body(String json) throws JsonProcessingException {
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

        // Obtén el valor del campo "body"
        String body = textNode.path("body").asText();
        return body;
    }



    public String extract_number(String json) throws JsonProcessingException {
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


        String numberwpp=firstMessage.path("from").asText();

       return  numberwpp;


    }

    public String extract_name(String json)throws  JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(json);

        JsonNode entryArray = rootNode.path("entry");
        // Asumiendo que quieres acceder al primer objeto del array "entry"
        JsonNode firstEntry = entryArray.get(0);

        JsonNode changesArray = firstEntry.path("changes");

        JsonNode firstChange = changesArray.get(0);
        JsonNode valueNode = firstChange.path("value");
        // Navega al campo "changes"

        JsonNode contacsArray = valueNode.path("contacts");



        // Asumiendo que quieres acceder al primer objeto del array "messages"
        JsonNode firstMessage = contacsArray.get(0);
        JsonNode namenode = firstMessage.path("profile");
        String name=namenode.path("name").asText();
        return name;
    }





        /* FUNCION NO VALIDA IN HERE
        Long id_intention=User_verify(numberwpp,body);

        if (id_intention!=null){
            Optional<Intentention_Entity> intencion= intentionService.finByid(id_intention);
            System.out.println(intencion.get().getId_message());
            Optional<Messages_Entity> mensaje= messageService.finById(intencion.get().getId_message());
            System.out.println(mensaje.get().getMessage());
            basicMessages.response_ISA(mensaje.get().getMessage());


        }*/







    /*public Long User_verify(String numberwpp,String message){

         if(consultado!=null && consultado.getCONTEXTO()==null){
             System.out.println(consultado.getName());
             Long Id_context=centrallController.intention_exam(message);
             userService.setcontext(Id_context,consultado);
             return  Id_context;
         }
     return null;


    }*/











}
