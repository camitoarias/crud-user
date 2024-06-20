package com.example.db.Admin_Users.Entity.Controller;

import com.example.db.Admin_Users.Entity.Admin_user;
import com.example.db.Admin_Users.Entity.DAO.Admin_Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controler_Admin {


    @Autowired
    private  Admin_Service adminService;

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







}
