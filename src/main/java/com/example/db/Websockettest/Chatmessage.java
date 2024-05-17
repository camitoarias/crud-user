package com.example.db.Websockettest;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chatmessage
{
    String message;
    String user;
}
