package com.example.db.Ite_chat_user.Infraestructure;

import com.example.db.Ite_chat_user.Domain.User_chatbot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends CrudRepository<User_chatbot, Long> {

    User_chatbot findByPhoneNumber(String phoneNumber);

}
