package com.example.db.Admin_Users.Aplication;

import com.example.db.Admin_Users.Domain.Admin_user;
import com.example.db.Admin_Users.Infraestructure.Admin_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class Admin_Service_Imp implements  Admin_Service {


    @Autowired
    Admin_Repository adminRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }



    @Override
    public Admin_user findByUsername(String username) {
        Admin_user encontrado=adminRepository.findByUsername(username);
        return encontrado;
    }

    @Override
    public Admin_user save_Admin(Admin_user adminUser) {
        String encryptedPassword = encryptPassword(adminUser.getPassword());
        System.out.println("contraseña:"+encryptedPassword);
        adminUser.setPassword(encryptedPassword);
        return adminRepository.save(adminUser);
    }




}
