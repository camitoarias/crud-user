package com.example.db.Actions.Aplication.ServiceCrud;

import com.example.db.Actions.Domain.CommandEntity;
import com.example.db.Actions.Infraestructure.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandService_Imp implements  CommandService{

    @Autowired
    CommandRepository commandRepository;

    @Override
    public CommandEntity findByCommandKey(String commandKey) {
        return null;
    }

    @Override
    public CommandEntity findByid(Long ID_ACTION) {
        Optional<CommandEntity> comando= commandRepository.findById(ID_ACTION);
        return comando.get();
    }
}
