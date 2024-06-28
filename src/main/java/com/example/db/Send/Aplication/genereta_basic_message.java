package com.example.db.Send.Aplication;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpMethod;


@Component
public class genereta_basic_message {

    @Autowired
    private RestTemplate restTemplate;

    //funcion para realizar el envio de un mensaje con el campo de texto del usuario que se encuentra activo en el front-end
    /** @param json_data  este sera el json con campo de usuario y mensaje enviado desde el front-end como el siguiente
    *<pre>
     *  {
     *      "username":"nomina";
     *      "mensaje":"este es un mensaje de ejemplo"
     *  }
     *
    *</pre>
     * @return JSONObject este es un json completamente listo para usar y en el campo "body" devuelve:
     * <pre>
     *     {
     *         "body":"enviado por username
     *                 este es un mensaje de prueba"
     *     }
     * </pre>
    */
    public JSONObject basic_message_front(String json_data){
        JSONObject jsonObject = new JSONObject(json_data);
        String user_name = jsonObject.getString("username");
        String message= jsonObject.getString("message");
        String mensajeTexto = "Enviado por " + user_name + "\n" + message;

        //json completo
        JSONObject finalMessageJSON = new JSONObject();
        //json anidado del campo de texto
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

    //PENDIENTE QUITAR
    //quitar por funcion redundante, funcion de send-messages

    public void sendPostRequest(String json) {
        String url = "https://waba-sandbox.360dialog.io/v1/messages";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("D360-API-KEY","5x96VJ_sandbox");

        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Respuesta del servidor: " + response.getBody());
        } else {
            System.err.println("Error en la solicitud: " + response.getStatusCode());
        }



}
//pendiente adaptar la funcion para que el numero sea responsivo, añadir el parametro
/**
 * @param message este es el simple mensaje de texto que queremos enviar
 * @return finalMessageJSON este es el json enviado para crear un mensaje sencillo
 * <pre>
 *     {
 *     "messaging_product": "whatsapp",
 *     "recipient_type": "individual",
 *     "to": "your-number",
 *     "type": "text",
 *     "text": {
 *         "body": (message)
 *     }
 * }
 * </pre>
 */
public JSONObject chatbot_message_send(String message) {
    JSONObject finalMessageJSON = new JSONObject();

    JSONObject jsonMensajeTexto = new JSONObject();
    jsonMensajeTexto.put("body", message);

    finalMessageJSON.put("messaging_product", "whatsapp");
    finalMessageJSON.put("recipient_type", "individual");
    finalMessageJSON.put("to", "573248324121"); // Replace with appropriate recipient number
    finalMessageJSON.put("type", "text");
    finalMessageJSON.put("text",jsonMensajeTexto);
    return finalMessageJSON;

}





}

