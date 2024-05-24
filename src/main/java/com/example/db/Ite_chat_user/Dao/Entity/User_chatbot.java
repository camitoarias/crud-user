package com.example.db.Ite_chat_user.Dao.Entity;

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

    @Getter @Setter @Column(name = "Phone-Number")
    private String Phone_Number;

    @Getter @Setter @Column(name = "GH-ID")
    private String GH_ID;

    @Getter @Setter @Column(name = "TAG")
    private String Tag;

    @Getter @Setter @Column(name = "Intention")
    private String Intention;

    @Getter @Setter @Column(name = "Iteration-Number")
    private  String  Iteration_Number;
    @Getter @Setter @Column(name = "State")
    private String State;


    public User_chatbot() {
    }



    public User_chatbot(String name, String phone_Number, String GH_ID, String tag, String intention, String iteration_Number, String state) {
        Name = name;
        Phone_Number = phone_Number;
        this.GH_ID = GH_ID;
        Tag = tag;
        Intention = intention;
        Iteration_Number = iteration_Number;
        State=state;
    }








}
