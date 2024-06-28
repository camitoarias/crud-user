package com.example.db.Admin_Users.Infraestructure;

import com.example.db.Admin_Users.Aplication.Admin_Service;
import com.example.db.Admin_Users.Domain.Admin_user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controler_Admin {


    @Autowired
    private Admin_Service adminService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping ("test/find")
    public ResponseEntity<Void> find(@RequestBody  Admin_user adminUser){

        if(adminUser!=null){
            Admin_user administrador =adminService.findByUsername(adminUser.getUsername());
            if (administrador!=null){

                if(administrador.getUsername().equals(adminUser.getUsername()) && administrador.getPassword().equals(adminUser.getPassword()) ){
                    System.out.println("hemos encontrado el usuario puedes acceder");
                    return new ResponseEntity<>( HttpStatus.OK);
                }else{
                    System.out.println("error al validar los datos");
                }

            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    @RequestMapping("/v1/admin-save")
    public void save_user(){
        Admin_user administrador=new Admin_user();
        administrador.setUsername("ejemplo3");
        administrador.setPassword("contraseña segura");
        adminService.save_Admin(administrador);
    }







}
