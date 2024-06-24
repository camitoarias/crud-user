package com.example.db.Admin_Users.Aplication;

import com.example.db.Admin_Users.Domain.Admin_user;
import com.example.db.Admin_Users.Infraestructure.Admin_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
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
        return adminRepository.save(adminUser);
    }




}
