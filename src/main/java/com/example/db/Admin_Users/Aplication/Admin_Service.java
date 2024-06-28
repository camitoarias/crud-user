package com.example.db.Admin_Users.Aplication;

import com.example.db.Admin_Users.Domain.Admin_user;
import org.springframework.data.jpa.repository.Query;


public interface Admin_Service    {
    @Query("SELECT u FROM admin_users u WHERE u.Username = ?1")
    Admin_user findByUsername(String username);

    Admin_user save_Admin(Admin_user adminUser);

}
