package com.example.db.Admin_Users.Domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "admin_users")
public class Admin_user {

    @Getter @Setter @Column(name = "Username")
    private  String username;

    @Getter @Setter @Column(name = "Password")
    private  String Password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long ID;


    public Admin_user(String username1, String password) {
        username = username1;
        Password = password;
    }
}
