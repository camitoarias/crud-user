package com.example.db.Functions_Chatbot.Function_Extra;

import org.apache.catalina.connector.Request;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;




@Component
public class genereta_basic_message {

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject basic_message_front(String json_data){
        JSONObject jsonObject = new JSONObject(json_data);
        String user_name = jsonObject.getString("username");
        String message= jsonObject.getString("message");
        String mensajeTexto = "Enviado por " + user_name + "\n" + message;

        JSONObject finalMessageJSON = new JSONObject();
        JSONObject jsonMensajeTexto = new JSONObject();

        jsonMensajeTexto.put("body", mensajeTexto);
        JSONObject cabeceraJSON = new JSONObject();
        cabeceraJSON.put("D360-API-KEY", "rURohx_sandbox");



        finalMessageJSON.put("messaging_product", "whatsapp");
        finalMessageJSON.put("recipient_type", "individual");
        finalMessageJSON.put("to", "573248324121"); // Replace with appropriate recipient number
        finalMessageJSON.put("type", "text");
        finalMessageJSON.put("text",jsonMensajeTexto);

        System.out.println(finalMessageJSON);



        return  finalMessageJSON;
    }

    public void sendPostRequest(String json) {
        String url = "https://waba-sandbox.360dialog.io/v1/messages";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("D360-API-KEY","4ZFHix_sandbox");

        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Respuesta del servidor: " + response.getBody());
        } else {
            System.err.println("Error en la solicitud: " + response.getStatusCode());
        }



}
    public void chatbot_message_send(String message){
        JSONObject finalMessageJSON = new JSONObject();

        JSONObject jsonMensajeTexto = new JSONObject();
        jsonMensajeTexto.put("body", message);

        finalMessageJSON.put("messaging_product", "whatsapp");
        finalMessageJSON.put("recipient_type", "individual");
        finalMessageJSON.put("to", "573248324121"); // Replace with appropriate recipient number
        finalMessageJSON.put("type", "text");
        finalMessageJSON.put("text",jsonMensajeTexto);

        System.out.println(finalMessageJSON);
        sendPostRequest(finalMessageJSON.toString());
    }


}


