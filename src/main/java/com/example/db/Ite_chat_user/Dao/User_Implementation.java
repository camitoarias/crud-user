package com.example.db.Ite_chat_user.Dao;

import com.example.db.Ite_chat_user.Dao.Entity.User_chatbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import java.util.List;

@RestController
public class User_Implementation implements  User_Service{


    @Autowired
    User_Repository userRepository;

    @Override
    public User_chatbot save_userchat(User_chatbot userchat) {
        userRepository.save(userchat);
        return null;
    }

    @Override
    public List<User_chatbot> get_userschat() {
        System.out.println("hola eh traido algunos usuarios de la base de datos");

        Iterable<User_chatbot> iterable = userRepository.findAll();

        List<User_chatbot> lista_usuarios = convertirIterableALista(iterable);
       if (!lista_usuarios.isEmpty()) {
            User_chatbot camilo = lista_usuarios.get(0);
            System.out.println("Primer usuario: " + camilo.getName());
        } else {
            System.out.println("No hay usuarios en la base de datos.");
        }

        return lista_usuarios;


    }

    @Override
    public void delete_userbyID(Long UserID) {

        userRepository.deleteById(UserID);
    }

    @Override
    public User_chatbot updateuser(User_chatbot userChatbot, Long UserID) {
        Optional<User_chatbot> optionalUser = userRepository.findById(UserID);
        if (optionalUser.isPresent()) {
            User_chatbot existingUser = optionalUser.get();
            // Actualiza los campos necesarios
            existingUser.setName(userChatbot.getName());
            existingUser.setPhone_Number(userChatbot.getPhone_Number());
            existingUser.setTag(userChatbot.getTag());
            existingUser.setIntention(userChatbot.getIntention());
            existingUser.setState(userChatbot.getState());
            existingUser.setGH_ID(userChatbot.getGH_ID());
            existingUser.setIteration_Number(userChatbot.getIteration_Number());


            // Guarda el usuario actualizado
            return userRepository.save(existingUser);
        } else {
            System.out.println("Usuario no encontrado con ID: " + UserID);
            return null; // O lanza una excepción si prefieres
        }

    }

    private <T> List<T> convertirIterableALista(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }


}
