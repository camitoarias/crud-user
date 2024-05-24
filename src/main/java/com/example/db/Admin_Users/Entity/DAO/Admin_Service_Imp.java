package com.example.db.Admin_Users.Entity.DAO;

import com.example.db.Admin_Users.Entity.Admin_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Admin_Service_Imp implements  Admin_Service {


    @Autowired
    Admin_Repository adminRepository;

    @Override
    public Admin_user findByUsername(String username) {
        Admin_user encontrado=adminRepository.findByUsername(username);


        return encontrado;
    }

    @Override
    public Admin_user save_userchat(Admin_user adminUser) {
        Admin_user SST= new Admin_user("SST","sst1237");
        return adminRepository.save(adminUser);
    }

    @RequestMapping("/test/admin")
    public String save(){
        Admin_user SST= new Admin_user("SST","sst1237");
        save_userchat(SST);
        return " eh guardado mi usuario";
    }


}
