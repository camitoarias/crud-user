package com.example.db.Chatbot_ISA;

import com.example.db.Chatbot_ISA.Intention.DAO.Intention_Service;
import com.example.db.Chatbot_ISA.Intention.Intentention_Entity;
import com.example.db.Chatbot_ISA.Messages.DAO.Message_Service;
import com.example.db.Chatbot_ISA.Messages.Messages_Entity;
import com.example.db.Functions_Chatbot.Actions.CommandEntity;
import com.example.db.Functions_Chatbot.Actions.CommandRepository;
import com.example.db.Functions_Chatbot.Actions.CommandService;
import com.example.db.Functions_Chatbot.Function_Extra.INTENTION_ANALIZER.basic_intention;
import com.example.db.Functions_Chatbot.Function_Extra.JSON_FUNCTIONS.interpretar_json;
import com.example.db.Functions_Chatbot.Function_Extra.USERCHAT_ADD_FUNCTION.userchatbot_add;
import com.example.db.Functions_Chatbot.Send_messages.Basic_messages;
import com.example.db.GH_gestion.Exel_Conection.Exel_Controller;
import com.example.db.GH_gestion.Exel_Conection.Load_Exel;
import com.example.db.GH_gestion.Exel_Conection.Load_by_atributo;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import com.example.db.Ite_chat_user.Repository.User_Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.db.Functions_Chatbot.Function_Extra.CONTEXT_ANALAZER.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
public class        CentrallController {

    @Autowired
    Context_Analizer contextAnalizer;

    @Autowired
    basic_intention basicIntention;

    @Autowired
    interpretar_json interpretarJson;
    @Autowired
    Load_Exel loadExel;
    @Autowired
    userchatbot_add userchatbot_add;
    @Autowired
    User_Service userService;
    @Autowired
    Exel_Controller exelController;
    @Autowired
    Basic_messages basicMessages;
    @Autowired
    Message_Service messageService;
    @Autowired
    Intention_Service intentionService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    CommandService commandService;



    @RequestMapping("/test/exel")
    public String test_exel() throws IOException {
        //exelController.user_add_exel("573248324020");
        //exelController.consultar_admision("1040030080");


        return "funcion exel";
    }

    @PostMapping("/test/json")
    public String test_json(@RequestBody String json) throws IOException {
        //extract numero y verificar
        User_chatbot usuario_consulta = userchatbot_add.verify_exist(interpretarJson.extract_number(json));


        if (usuario_consulta == null) {
            test_exel();
        }


        //dar una intencion si no ay ninguna
        if (usuario_consulta.getCONTEXTO() == null) {
            //retorno de id de mensaje siguiente para posible guardado
            Long id_intention = basicIntention.intention_exam(interpretarJson.extract_body(json));

            if (id_intention != null) {
                userService.setcontext(id_intention, usuario_consulta);
                System.out.println("le he dado un contexto a el usuario" + usuario_consulta.getName());

            }


        }

        //analizar el contesto que tenia almacenado el usuario
        if (usuario_consulta.getCONTEXTO() != null) {
            System.out.println("inicie con contexto");
            String next_id = contextAnalizer.verify_context(usuario_consulta.getCONTEXTO(), interpretarJson.extract_body(json));
            System.out.println(interpretarJson.extract_body(json));
            System.out.println(Long.parseLong(next_id));
            userService.setcontext(Long.parseLong(next_id), usuario_consulta);
            System.out.println("termine de verificar un contexto");
        }


        return "funcion terminada";
    }


    @RequestMapping("/test/intention")
    public String test_intention() {
        Long nextmessage_ID = basicIntention.intention_exam("carta bancaria");
        System.out.println(nextmessage_ID);
        return "completo exitoso intention ";
    }

    @RequestMapping("/test/context")
    public String test_context() {
        String id = contextAnalizer.verify_context(1l, "nomina");
        //PENDIENTE PASO A LONG
        System.out.println(id);
        return id;
    }

    @PostMapping("/chatbot/recibir")
    public void Center_point(@RequestBody String json) throws IOException {
        String number_phone=interpretarJson.extract_number(json);
        User_chatbot usuario_entrante=userService.findByPhoneNumber(number_phone);
        //verificacion del usuario
        if(usuario_entrante==null){
            boolean exel_add=exelController.user_add_exel(number_phone);
            System.out.println("buscando en exel");
            if(exel_add==false){
                System.out.println("obteniendo nombre");
                usuario_entrante=new User_chatbot();
                usuario_entrante.setName(interpretarJson.extract_name(json));
                usuario_entrante.setPhoneNumber(number_phone);
                userService.save_userchat(usuario_entrante);
                basicIntention.intention_exam(interpretarJson.extract_body(json));
            }
        }
        User_chatbot usuario_analizar=userService.findByPhoneNumber(number_phone);




        System.out.println(usuario_analizar.getCONTEXTO());
        //analizar el contesto que tenia almacenado el usuario
        if (usuario_analizar.getCONTEXTO() != null) {


            String next_id = contextAnalizer.verify_context(usuario_analizar.getCONTEXTO(), interpretarJson.extract_body(json));
            if(next_id=="Action"){

               // Optional<Messages_Entity> mensaje_analizar= messageService.finById(usuario_analizar.getCONTEXTO());

                // Optional<CommandEntity> comando=commandRepository.findById(mensaje_analizar.get().getFunction_action());
                Optional<CommandEntity> comando=commandRepository.findById(usuario_analizar.getCONTEXTO());
                String key=comando.get().getCommandKey();
                commandService.execute(key,interpretarJson.extract_body(json),usuario_analizar);


                // revisar contexto si no esta vacio
            } else  if(next_id!=null) {
                        try{

                            System.out.println(interpretarJson.extract_body(json));
                            System.out.println("mensaje contexto"+Long.parseLong(next_id));
                            userService.setcontext(Long.parseLong(next_id), usuario_analizar);

                            Optional<Messages_Entity> mensaje= messageService.finById(Long.parseLong(next_id));

                           //responder luego de dar contexto

                            //para si lo respuesta siguiente es una accion directamente
                            if(next_id.startsWith("99")) {
                                System.out.println("sigue una accion");
                                Optional<CommandEntity> comando=commandRepository.findById(Long.valueOf(next_id));
                                String key=comando.get().getCommandKey();
                                commandService.execute(key,interpretarJson.extract_body(json),usuario_analizar);

                            }else{
                                basicMessages.response_ISA(mensaje.get().getMessage());
                            }
                        } catch (JsonProcessingException e) {
                            userService.setcontext(null,usuario_analizar);
                            throw new RuntimeException(e);
                        } catch (NumberFormatException e) {
                            userService.setcontext(null,usuario_analizar);

                            throw new RuntimeException(e);
                        }




            //si no se reconoce contexto o no ay mensaje dar contexto nulo
            } else if(next_id==null){
                    userService.setcontext(null,usuario_analizar);


            }


        }




        //dar una intencion si no ay ninguna
        if (usuario_analizar.getCONTEXTO() == null) {
            //retorno de id de mensaje siguiente para posible guardado
            Long id_intention = basicIntention.intention_exam(interpretarJson.extract_body(json));
            if (id_intention != null) {
               userService.setcontext(id_intention, usuario_analizar);
                Optional<Messages_Entity> mensaje_next=messageService.finById(id_intention);
                System.out.println(mensaje_next.get().getMessage());
                basicMessages.response_ISA(mensaje_next.get().getMessage());
                System.out.println(id_intention);


            }else{
                basicMessages.response_ISA("lo siento no he podido reconocer su intencion");
            }
        }






        }
}
















