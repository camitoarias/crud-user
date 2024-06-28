package com.example.db.Send.Aplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SendMessage {

    @Autowired
    private RestTemplate restTemplate;


    /**
     *
     * @param json es el body del json que vamos a enviar a 360 dialog, para finalmente añadirle el header siguiente
     *             <prev>
     *             {
     *                 "D360-API-KEY":"valor de la clave del sandbox";
     *             }
     *             </prev>
     *             <b>ES MUY POSIBLE QUE EL HEADER CAMBIE</b>
     */
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
}
