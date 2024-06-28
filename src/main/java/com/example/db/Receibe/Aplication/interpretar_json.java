package com.example.db.Receibe.Aplication;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * JSON del webhook que se envia al servidor:
 * <pre>
 * {
 *   "object": "whatsapp_business_account",
 *   "entry": [
 *     {
 *       "id": "180400265161288",
 *       "changes": [
 *         {
 *           "value": {
 *             "messaging_product": "whatsapp",
 *             "metadata": {
 *               "display_phone_number": "4930609859535",
 *               "phone_number_id": "176835862185450"
 *             },
 *             "contacts": [
 *               {
 *                 "profile": {
 *                   "name": "camitoarias"
 *                 },
 *                 "wa_id": "57123456"
 *               }
 *             ],
 *             "messages": [
 *               {
 *                 "from": "57123456",
 *                 "id": "wamid.HBgMNTczMjQ4MzI0MTIxFQIAEhggNDk3NUE3NjU3QjMxNUZDRjkzRkQzOEZENTFCNDBBM0IA",
 *                 "timestamp": "1716842398",
 *                 "text": {
 *                   "body": "quiero saber si pase"
 *                 },
 *                 "type": "text"
 *               }
 *             ]
 *           },
 *           "field": "messages"
 *         }
 *       ]
 *     }
 *   ]
 * }
 * </pre>
 */
@Component
public class interpretar_json {


    /**
     *
     * @param json es el json completo del webhook
     * @return retorna el valor del body, que seria el texto plano que envio el usuario
     * <pre>
     *               {
     *                 "from": "57123456",
     *                 "id": "wamid.HBgMNTczMjQ4MzI0MTIxFQIAEhggNDk3NUE3NjU3QjMxNUZDRjkzRkQzOEZENTFCNDBBM0IA",
     *                 "timestamp": "1716842398",
     *                 "text": {
     *                   <b>"body": "quiero saber si pase"</b>
     *                 },
     *                 "type": "text"
     *               }
     * </pre>

     * @throws JsonProcessingException
     */
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



    /**
     *
     * @param json es el json completo del webhook
     * @return retorna el valor del numero que envio ese mensaje
     *              <pre>
     *                        {
     *                        <b>"from": "57123456",</b>
     *      *                 "id": "wamid.HBgMNTczMjQ4MzI0MTIxFQIAEhggNDk3NUE3NjU3QjMxNUZDRjkzRkQzOEZENTFCNDBBM0IA",
     *      *                 "timestamp": "1716842398",
     *      *                 "text": {
     *      *                   "body": "quiero saber si pase"
     *      *                 },
     *      *                 "type": "text"
     *                  }
     *              </pre> {

     * @throws JsonProcessingException
     */
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

    /**
     *
     * @param json es el json completo del webhook
     * @return retorna el valor del name, para casos que no hayan datos del usuario
     * <pre>
     *      *             "contacts": [
     *  *               {
     *  *                 "profile": {
     *  *                   <b>"name": "camitoarias"</b>
     *  *                 },
     *  *                 "wa_id": "57123456"
     *  *               }
     *  *             ],
     * </pre>
     * @throws JsonProcessingException
     */
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
