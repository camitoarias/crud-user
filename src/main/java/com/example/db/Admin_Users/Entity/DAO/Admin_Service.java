package com.example.db.Admin_Users.Entity.DAO;

import com.example.db.Admin_Users.Entity.Admin_user;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;



public interface Admin_Service    {
    @Query("SELECT u FROM admin_users u WHERE u.Username = ?1")
    Admin_user findByUsername(String username);

    Admin_user save_userchat(Admin_user adminUser);

}
