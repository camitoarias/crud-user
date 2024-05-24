package com.example.db.Ite_chat_user.Dao;

import com.example.db.Ite_chat_user.Dao.Entity.User_chatbot;

import java.util.List;

public interface User_Service {
    User_chatbot save_userchat(User_chatbot userchat);

    public List<User_chatbot> get_userschat();

    void delete_userbyID(Long UserID);

   //update user-cahtbotisa
    User_chatbot updateuser(User_chatbot userChatbot, Long UserID);

}
