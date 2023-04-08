package com.chatapplication.personalchat;

import com.chatapplication.userdetails.User;

public interface PersonalChatViewToControllerCall {

	void addFriend(User user, String string2);

	void getPersonalChats(User user);

	void addMessage(User user, String personId, String msg, String loginerId, String status);

	void setMessagesViewed(String status, String loginerId);

	void closeConnection();

}
