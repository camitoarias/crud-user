package com.example.db.Actions.Aplication.ServiceCrud;

import com.example.db.Actions.Domain.CommandEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommandService {
    CommandEntity findByCommandKey(String commandKey);


    CommandEntity findByid(Long ID_ACTION);


}
