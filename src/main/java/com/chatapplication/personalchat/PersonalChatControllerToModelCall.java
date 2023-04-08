package com.chatapplication.personalchat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapplication.userdetails.User;

public interface PersonalChatControllerToModelCall {

	void addFriend(User user, String string2);

	void getPersonalChats(User user, HttpServletRequest req, HttpServletResponse res);

	void addMessage(User user, String personId, String msg, String loginerId, String status);

	void setMessageViewed(String status, String loginerId);

	void closeConnection();

}
