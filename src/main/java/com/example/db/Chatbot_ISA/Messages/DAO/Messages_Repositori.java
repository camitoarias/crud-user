package com.example.db.Chatbot_ISA.Messages.DAO;


import com.example.db.Chatbot_ISA.Messages.Messages_Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Messages_Repositori extends CrudRepository<Messages_Entity, Long> {
}
