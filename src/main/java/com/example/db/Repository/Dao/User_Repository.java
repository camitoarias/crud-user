package com.example.db.Repository.Dao;

import com.example.db.Entity.User_chatbot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends CrudRepository<User_chatbot, Long> {
}
