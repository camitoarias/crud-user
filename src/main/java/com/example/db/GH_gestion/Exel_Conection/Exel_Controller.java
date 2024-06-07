package com.example.db.GH_gestion.Exel_Conection;


import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class Exel_Controller {
    @Autowired
    Load_by_atributo loadByAtributo;
    @Autowired
    Load_Exel loadExel;
    @Autowired
    Load_by_Row loadByRow;
    @Autowired
    User_Service userService;


    public boolean user_add_exel(String numero) throws IOException {
        Sheet hoja=loadExel.Cargar_Archivo("/home/camito/Documentos/exel_files/example.xlsx");
        Row fila=loadByAtributo.buscar_por_atributo_center(numero,"telefono",hoja);
        if(fila!=null){
            List<String> cabeceras=new ArrayList<>(Arrays.asList("nombre","gh_id","cedula"));
            Map<Integer,String> cabeceras_indice= loadByRow.getHeaderIndices(cabeceras,hoja);
            Map<String,String> valores=loadByRow.getValuesFromRow(fila,cabeceras_indice);
            User_chatbot userChatbot=new User_chatbot();
            userChatbot.setName(valores.get("nombre"));
            userChatbot.setGH_ID(valores.get("gh_id"));
            userChatbot.setCedula(valores.get("cedula"));
            userChatbot.setPhoneNumber(numero);
            userService.save_userchat(userChatbot);
            return true;

        }else{return false;}



    }

    public Map<String, String> consultar_admision(String cedula) throws IOException {
       try{

           Sheet hoja=loadExel.Cargar_Archivo("/home/camito/Documentos/exel_files/example.xlsx");
           Row fila=loadByAtributo.buscar_por_atributo_center(cedula,"cedula",hoja);
           List<String> cabeceras=new ArrayList<>(Arrays.asList("estado_admision"));
           Map<Integer,String> cabeceras_indice= loadByRow.getHeaderIndices(cabeceras,hoja);
           Map<String,String> valores=loadByRow.getValuesFromRow(fila,cabeceras_indice);
           return valores;
       } catch (IOException e) {
           return null;
       }


    }



}
