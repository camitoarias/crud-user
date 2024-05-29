package com.example.db.Ite_chat_user.Repository;

import com.example.db.Ite_chat_user.Entity.User_chatbot;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface User_Service {
    User_chatbot save_userchat(User_chatbot userchat);

    public List<User_chatbot> get_userschat();

    void delete_userbyID(Long UserID);

   //update user-cahtbotisa
    User_chatbot updateuser(User_chatbot userChatbot, Long UserID);

   @Query("SELECT u FROM usuarios u WHERE u.phoneNumber =?1")
    User_chatbot findByPhoneNumber(String phoneNumber);

   User_chatbot setcontext(Long context,User_chatbot user_context);



}
