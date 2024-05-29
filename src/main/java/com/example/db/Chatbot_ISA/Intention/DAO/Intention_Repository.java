package com.example.db.Chatbot_ISA.Intention.DAO;

import com.example.db.Admin_Users.Entity.Admin_user;
import com.example.db.Chatbot_ISA.Intention.Intentention_Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Intention_Repository extends CrudRepository<Intentention_Entity, Long> {


}
