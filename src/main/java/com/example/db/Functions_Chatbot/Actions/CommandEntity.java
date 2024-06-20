package com.example.db.Functions_Chatbot.Actions;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="command")
@NoArgsConstructor
public class CommandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter
    private Long id;

    @Getter @Setter
    private String commandKey;
    @Setter @Getter
    private String className;

    public CommandEntity(String commandKey, String className) {
        this.commandKey = commandKey;
        this.className = className;
    }
}