package com.chatapplication.loginorregisterpage;

import com.chatapplication.userdetails.User;

public interface LoginOrRegisterPageViewToControllerCall {

	void setUserDetails(User user);

	void checkLogin(User user);

	void closeConnection();

}
