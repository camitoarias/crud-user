package com.example.db.Chatbot_ISA.Intention.DAO;

import com.example.db.Chatbot_ISA.Intention.Intentention_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Intention_IMP implements  Intention_Service{

    @Autowired
    Intention_Repository intentionRepository;


    @Override
    public Intentention_Entity save_intention(Intentention_Entity intention) {
        return intentionRepository.save(intention);

    }

    @Override
    public List<Intentention_Entity> load_intention() {
        Iterable<Intentention_Entity> iterable=  intentionRepository.findAll();
        List<Intentention_Entity> list_intention=convertirIterableALista(iterable);
        return list_intention;

    }

    @Override
    public Optional<Intentention_Entity> finByid(Long id) {
        Optional<Intentention_Entity> intencion=intentionRepository.findById(id);
        return intencion;

    }

    @RequestMapping("/test/intention")
    public String intention_test(){
        String[] reconocimiento= {"hola","buenos dias","buenas tardes"};
        String[] obligatorio=null;
        reconocimiento= new String[]{String.join("^", reconocimiento)};
        Intentention_Entity intension=new Intentention_Entity("name","","",reconocimiento,obligatorio,6l);
        save_intention(intension);

        return "intencion agregada";
    }



    private <T> List<T> convertirIterableALista(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}
