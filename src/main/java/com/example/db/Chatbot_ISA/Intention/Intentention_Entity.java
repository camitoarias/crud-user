package com.example.db.Chatbot_ISA.Intention;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="intention")
public class Intentention_Entity {

    @Column(name="name")
    String name;

    @Column(name="Area")
    String Area;

    @Column(name="Contexto")
    String contexto;

    @Column(name="Reconocimiento")
    String  Reconocimiento;

    @Column(name="Obligatorio")
    String Obligatorio_campos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;

    @Getter @Setter @Column(name="message")
    Long Id_message;

    public Intentention_Entity(String name, String area, String contexto, String[] reconocimiento, String[] obligatorio_campos,Long id_message) {
        this.name = name;
        Area = area;
        this.contexto = contexto;
        Reconocimiento = Arrays.toString(reconocimiento);
        Obligatorio_campos = Arrays.toString(obligatorio_campos);
        this.Id_message=id_message;
    }


}
