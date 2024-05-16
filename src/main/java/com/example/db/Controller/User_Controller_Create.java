package com.example.db.Controller;

import com.example.db.Repository.Dao.User_Repository;
import com.example.db.Repository.Dao.User_Service;
import com.example.db.Entity.User_chatbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class User_Controller_Create {

    @Autowired
    private User_Service userService;

    @RequestMapping("/testUser")
    public String UserchatCreate(){
        User_chatbot usuario = new User_chatbot("usuario para react","315789654","100","front-end","mostrar","5","adminitido");

        userService.save_userchat(usuario);

        return "hola espero haber creado mi usuario";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping ("/listuser")
    public List<User_chatbot> getusuarios(){
        List<User_chatbot> lista= userService.get_userschat();
        return  lista;
    }

    @RequestMapping("/deletebyid")
    public String Delete_userChat(){
        Long ID= 2L;
       userService.delete_userbyID(ID);
       return "creo que ya te he eliminado de mi vida";

    }

    @RequestMapping("/updateuser")
    public String update_userchat(){
        User_chatbot usuario = new User_chatbot("cambio","4002545","50","retiro","despedida","7","finalizado");
        long ID =3l;
        userService.updateuser(usuario,ID);
        return "creo que he modificado tu vida y tocado tu corazon";
    }






}
