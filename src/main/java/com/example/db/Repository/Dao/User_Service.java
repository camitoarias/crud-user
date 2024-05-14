package com.example.db.Repository.Dao;

import com.example.db.Entity.User_chatbot;
import org.apache.catalina.User;

import java.util.List;

public interface User_Service {
    User_chatbot save_userchat(User_chatbot userchat);

    public List<User_chatbot> get_userschat();

    void delete_userbyID(Long UserID);

   //update user-cahtbotisa
    User_chatbot updateuser(User_chatbot userChatbot, Long UserID);

}
