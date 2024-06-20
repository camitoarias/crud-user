package com.example.db.Ite_chat_user.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuarios")
public class User_chatbot {
    @Getter @Setter @Column(name = "Name")
    private String Name;

    @Getter @Setter @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue
    private long ID;

    @Getter @Setter @Column(name = "phonenumber")
    private String phoneNumber;




    @Getter @Setter @Column(name ="Estado_asociado")
    private Long State;

    @Getter @Setter @Column(name="CEDULA")
    private String Cedula;


    public User_chatbot() {
    }



    public User_chatbot(String name, String phoneNumber, String GH_ID, String tag, String intention, String iteration_Number) {
        Name = name;
        this.phoneNumber = phoneNumber;


    }








}
