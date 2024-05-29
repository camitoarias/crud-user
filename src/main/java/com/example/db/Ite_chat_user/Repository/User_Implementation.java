package com.example.db.Ite_chat_user.Repository;

import com.example.db.Ite_chat_user.Entity.User_chatbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import java.util.List;


public class User_Implementation implements User_Service {


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
            existingUser.setPhoneNumber(userChatbot.getPhoneNumber());
            existingUser.setTag(userChatbot.getTag());
            existingUser.setIntention(userChatbot.getIntention());

            existingUser.setGH_ID(userChatbot.getGH_ID());
            existingUser.setIteration_Number(userChatbot.getIteration_Number());


            // Guarda el usuario actualizado
            return userRepository.save(existingUser);
        } else {
            System.out.println("Usuario no encontrado con ID: " + UserID);
            return null; // O lanza una excepción si prefieres
        }

    }

    @Override
    public User_chatbot findByPhoneNumber(String phonenumber) {
        User_chatbot usuario_busqueda=userRepository.findByPhoneNumber(phonenumber);
        
        return  usuario_busqueda;
    }

    @Override
    public User_chatbot setcontext(Long context, User_chatbot usuario_context) {
        usuario_context.setCONTEXTO(context);
        return userRepository.save(usuario_context);
    }

    private <T> List<T> convertirIterableALista(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }


}
