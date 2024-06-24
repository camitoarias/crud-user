package com.example.db.Actions.Infraestructure;

import com.example.db.Actions.Aplication.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }


   /* @RequestMapping("/test/actionconsul")
    public String consultar2() throws IOException {
        String commandKey = "consultar_estado_admision";
        String input = "1040030080";
        return commandService.execute(commandKey,input);
    }*/
}
