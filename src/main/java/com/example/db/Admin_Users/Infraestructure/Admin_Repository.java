package com.example.db.Admin_Users.Infraestructure;

import com.example.db.Admin_Users.Domain.Admin_user;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Admin_Repository extends CrudRepository<Admin_user, Long> {

    Admin_user findByUsername(String Username);
}
