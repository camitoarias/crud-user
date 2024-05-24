package com.example.db.Ite_chat_user.Dao;

import com.example.db.Ite_chat_user.Dao.Entity.User_chatbot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends CrudRepository<User_chatbot, Long> {



}
