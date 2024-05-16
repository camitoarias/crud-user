package com.example.db.Controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Message {

    private String Message_text;
    private String datos;


    @Id private long ID;


    public Message() {
    }

    public Message(String message, String datos, long id) {
        this.Message_text = message;
        this.datos = datos;
        this.ID= id;
    }
}
