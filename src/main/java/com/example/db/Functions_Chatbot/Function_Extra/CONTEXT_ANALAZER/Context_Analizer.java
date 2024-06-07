package com.example.db.Functions_Chatbot.Function_Extra.CONTEXT_ANALAZER;

import com.example.db.Chatbot_ISA.Messages.DAO.Message_Service;
import com.example.db.Chatbot_ISA.Messages.Messages_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//PENDIENTE MODIFICACION DE NOMBRES VARIABLES
@Component
public class Context_Analizer {


    @Autowired
    Message_Service messageService;



    //punto central con contexto y se verifica si la respuesta era la esperada , retorna id de mensaje siguiente
    public String verify_context(Long id_context, String Answer){

        //el id context es el mensaje siguiente
         String id_verifi= String.valueOf(id_context);
        if(id_verifi.startsWith("99")){
            System.out.println("supongamos que ejecute una accion ");
            return "Action";

        }else{
            Optional<Messages_Entity> mensaje_analizar= messageService.finById(id_context);//PENDIENTE CAMBIO PARA CONTROLADOR del mensaje service
            String id_message= String.valueOf(mensaje_analizar.get().getID());

            //String de las respuestas guardadas en la base de datos
            String complet_message=mensaje_analizar.get().getRespuestas();
            System.out.println(complet_message);

            List<Map<String, Object>> separeted_messages=procesarRespuestas(complet_message);
            System.out.println(separeted_messages);
            //  String id_message_response=obtenerAsociacion(separeted_messages,Answer);//funcion con un unico map
            String id_message_response=obtenerAsociacion(separeted_messages,Answer);

            if (id_message_response!=null){
                return id_message_response;
            }else{


            }
return null;
         }



    }




    //verifica si respondio algo con una posible respuesta y devuelve el Long del mensaje siguiente
    public static String obtenerAsociacion(Map<String, Object> datos, String parametro) {
        List<String> elementos = (List<String>) datos.get("elementos");
        String asociacion = (String) datos.get("asociacion");

        System.out.println(elementos);
        if (elementos.contains(parametro)) {
            return asociacion;
        } else {
            return null;
        }
    }




    //crea un tipo map casi json que tiene un elemento array con las respuestas y estan asociadas a un id
    /*public static Map<String, Object> parsearCadena(String cadena) {
        Map<String, Object> resultado = new HashMap<>();
        List<String> elementos = new ArrayList<>();

        // Encuentra la parte dentro de los corchetes
        String[] partes = cadena.split("\\{");
        String elementosStr = partes[0].substring(1, partes[0].length() - 1); // "1","ejemplo"
        String asociacion = partes[1].substring(0, partes[1].length() - 1); // 1101.1

        // Separa los elementos
        String[] elementosArray = elementosStr.split(",");

        for (String elemento : elementosArray) {
            String elementoLimpio = elemento.replaceAll("\"", ""); // Quita las comillas
            elementos.add(elementoLimpio);
        }

        // Agrega los elementos y la asociación al resultado
        resultado.put("elementos", elementos);
        resultado.put("asociacion", asociacion);

        System.out.println(resultado);

        return resultado;
    }*/


    //generar la lista de map con el string de respuestas en DB
    public static List<Map<String, Object>> procesarRespuestas(String respuestas) {
        List<Map<String, Object>> resultado = new ArrayList<>();

        // Definir una expresión regular para capturar los elementos y la asociación
        Pattern pattern = Pattern.compile("\\[(.*?)\\]\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(respuestas);

        // Iterar sobre las coincidencias y construir los mapas
        while (matcher.find()) {
            String elementosStr = matcher.group(1);
            String asociacionStr = matcher.group(2);

            // Dividir los elementos por comas, teniendo en cuenta las comillas alrededor de los elementos
            String[] elementosArray = elementosStr.split("\",\"");
            List<String> elementos = new ArrayList<>();
            for (String elemento : elementosArray) {
                elementos.add(elemento.replace("\"", "").trim());
            }

            // Crear un mapa y agregarlo a la lista de resultados
            Map<String, Object> map = new HashMap<>();
            map.put("elementos", elementos);
            map.put("asociacion", asociacionStr);
            resultado.add(map);
        }

        return resultado;
    }

    public String obtenerAsociacion(List<Map<String, Object>> listaMapas, String parametro) {
        for (Map<String, Object> mapa : listaMapas) {
            List<String> elementos = (List<String>) mapa.get("elementos");
            if (elementos != null && elementos.contains(parametro)) {
                return (String) mapa.get("asociacion");
            }
        }
        return null; // Devuelve null si no hay coincidencia
    }
}
