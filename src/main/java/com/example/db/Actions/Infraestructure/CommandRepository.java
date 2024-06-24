package com.example.db.Actions.Infraestructure;

import com.example.db.Actions.Domain.CommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<CommandEntity, Long> {
    CommandEntity findByCommandKey(String commandKey);
}
