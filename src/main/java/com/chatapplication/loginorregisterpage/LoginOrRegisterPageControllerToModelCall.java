package com.chatapplication.loginorregisterpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapplication.userdetails.User;

public interface LoginOrRegisterPageControllerToModelCall {

	void setUserDetails(User user);

	void checkLogin(User user, HttpServletResponse res, HttpServletRequest req);

	void closeConnection();

}
