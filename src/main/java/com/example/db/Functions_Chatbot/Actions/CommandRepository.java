package com.example.db.Functions_Chatbot.Actions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<CommandEntity, Long> {
    CommandEntity findByCommandKey(String commandKey);
}
