package com.example.db.Actions.Infraestructure;


import com.example.db.Actions.Domain.CommandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.db.Actions.Aplication.ServiceCrud.CommandService;

@RestController
public class CommandController {

    private final CommandService commandService;


    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }



    @RequestMapping("/test/action")
    public String action_test(){
        CommandEntity comando=commandService.findByid(990011l);
        System.out.println(comando.getCommandKey());
        return "prueba finalizada acction";
    }


    //una prueba para el llamado de una accion con un command key y un input manual
   /* @RequestMapping("/test/actionconsul")
    public String consultar2() throws IOException {
        String commandKey = "consultar_estado_admision";
        String input = "1040030080";
        return commandService.execute(commandKey,input);
    }*/
}
