package com.example.db.Actions.Aplication;

import java.io.IOException;
import java.util.List;

import com.example.db.Actions.Domain.CommandEntity;
import com.example.db.Actions.Infraestructure.CommandRepository;
import com.example.db.Ite_chat_user.Domain.User_chatbot;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Servicio para manejar los comandos del chatbot.
 */
@Service
public class CommandService {

    // Mapa concurrente para almacenar los comandos disponibles, indexados por sus claves.
    private final Map<String, Command> commandMap = new ConcurrentHashMap<>();
    // Repositorio para acceder a los datos de los comandos almacenados en la base de datos.
    private final CommandRepository commandRepository;
    // Contexto de la aplicación para obtener beans de Spring.

    private final ApplicationContext context;

    /**
     * Constructor del servicio CommandService.
     *
     * @param commandRepository Repositorio para acceder a los datos de los comandos.
     * @param context Contexto de la aplicación para obtener beans de Spring.
     * <b>importante todos los comandos son cargados y almacenados en un map al inciar este servicio</b>
     *                  <p>
     *
     *  * <blockquote>
     *  <b>este es un servicio provisional y no se planea que sea implementado en el producto terminado </b>
     *  * </blockquote>
     *  * </p>
     */
    @Autowired
    public CommandService(CommandRepository commandRepository, ApplicationContext context) {
        this.commandRepository = commandRepository;
        this.context = context;
        loadCommandsFromDatabase(); // Carga los comandos desde la base de datos al inicializar el servicio.
    }

    /**
     * Carga los comandos desde la base de datos y los almacena en el mapa de comandos.
     */
    //pendiente optimizacion no cargar todos los comandos solo buscar el comando nesesario
    private void loadCommandsFromDatabase() {
        List<CommandEntity> commands = commandRepository.findAll(); // Obtiene todos los comandos de la base de datos.
        System.out.println(commands);
        for (CommandEntity commandEntity : commands) {
            System.out.println(commandEntity.getClassName());
            try {
                // Carga la clase del comando dinámicamente utilizando su nombre de clase.
                Class<?> clazz = Class.forName(commandEntity.getClassName());
                // Obtiene una instancia del bean del comando desde el contexto de Spring.
                Command command = (Command) context.getBean(clazz);
                // Almacena el comando en el mapa utilizando su clave.
                commandMap.put(commandEntity.getCommandKey(), command);
            } catch (ClassNotFoundException e) {
               // e.printStackTrace();
                System.out.println("comando no encontrado");
            }
        }
    }

    /**
     * Ejecuta un comando dado su clave, entrada y usuario.
     *
     * @param commandKey Clave del comando a ejecutar.
     * @param input Entrada proporcionada al comando.
     * @param usuario Usuario que ejecuta el comando.
     * @return Resultado de la ejecución del comando.
     * @throws IOException Si ocurre un error de E/S durante la ejecución del comando.
     */

    public String execute(String commandKey, String input, User_chatbot usuario) throws IOException {
        loadCommandsFromDatabase(); // Recarga los comandos desde la base de datos antes de ejecutar.
        Command command = commandMap.get(commandKey); // Obtiene el comando correspondiente a la clave proporcionada.

        if (command != null) {
            // Ejecuta el comando y devuelve el resultado.
            return command.execute(input, usuario);
        } else {
            // Devuelve un mensaje de error si el comando no se encuentra.
            return "Unknown command";
        }
    }
}
