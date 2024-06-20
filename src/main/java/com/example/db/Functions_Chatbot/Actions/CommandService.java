package com.example.db.Functions_Chatbot.Actions;

import java.io.IOException;
import java.util.List;

import com.example.db.Functions_Chatbot.Actions.Entity.CommandEntity;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommandService {

    private final Map<String, Command> commandMap = new ConcurrentHashMap<>();
    private final CommandRepository commandRepository;
    private final ApplicationContext context;

    @Autowired
    public CommandService(CommandRepository commandRepository, ApplicationContext context) {
        this.commandRepository = commandRepository;
        this.context = context;
        loadCommandsFromDatabase();
    }

    private void loadCommandsFromDatabase() {
        List<CommandEntity> commands = commandRepository.findAll();
        System.out.println(commands);
        for (CommandEntity commandEntity : commands) {

            System.out.println(commandEntity.getClassName());
            try {
                Class<?> clazz = Class.forName(commandEntity.getClassName());
                Command command = (Command) context.getBean(clazz);
                commandMap.put(commandEntity.getCommandKey(), command);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public String execute(String commandKey, String input, User_chatbot usuario) throws IOException {
       loadCommandsFromDatabase();
        Command command = commandMap.get(commandKey);

        if (command != null) {
            return command.execute(input,usuario);
        } else {
            return "Unknown command";
        }
    }


}
