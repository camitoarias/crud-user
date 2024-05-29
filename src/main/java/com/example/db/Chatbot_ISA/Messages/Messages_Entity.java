package com.example.db.Chatbot_ISA.Messages;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.generator.values.GeneratedValues;

import java.lang.reflect.Array;
import java.util.Arrays;

@Entity
@NoArgsConstructor
@Table(name="messages")
@Getter
@Setter
public class Messages_Entity {

    @Getter @Setter @Column(name="Name")
    String Name;
    @Getter @Setter @Column(name="Message")
    String Message;
    @Getter @Setter @Column(name="Area")
    String Area;
    @Getter @Setter @Column(name="Contexto")
    String  Contexto;
    @Getter @Setter @Column(name="Respuestas")
    String Respuestas;
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long ID;

    public Messages_Entity(String name, String message, String area, String contexto, String[] respuestas) {
        Name = name;
        Message = message;
        Area = area;
        Contexto = contexto;
        Respuestas = Arrays.toString(respuestas);
    }
}
